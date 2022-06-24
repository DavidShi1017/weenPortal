package com.jingtong.platform.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    // 设置导出excle文件对应的response属性信息
    public static void setExcelResponseInfo(HttpServletRequest request, HttpServletResponse response, String fileName) {
        response.setContentType("application/binary;charset=utf-8");
        response.setHeader("Cache-Control", "private");
        response.setHeader("Pragma", "private");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Type", "application/force-download");
        fileName = CommonUtil.processFileName(request, fileName);
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
    }

    // 处理导出Excle文件中文乱码问题
    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");
                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }

    public static List<Map<String, String>> listBean2ListMap(List<T> listobj) {
        if (listobj == null) {
            return null;
        }
        List<Map<String, String>> li = new ArrayList<Map<String, String>>();
        for (T p : listobj) {
            li.add(CommonUtil.transBean2Map(p));
        }
        return li;
    }

    // bean对象转成Map对象
    public static Map<String, String> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, String.valueOf(value));
                }

            }
        } catch (Exception e) {
            LOGGER.error("transBean2Map Error {}", e);
        }
        return map;

    }

    // 导出Excel
    public static void exportExcel(List<Map<String, String>> list, String[] titles, String[] keys,
            ServletOutputStream outputStream) throws Exception {
        try {
            // 创建一个workbook 对应一个excel应用文件
            // XSSFWorkbook workBook = new XSSFWorkbook();

            int rowaccess = 100;// 内存中缓存记录行数
            /* keep 100 rows in memory,exceeding rows will be flushed to disk */
            SXSSFWorkbook workBook = new SXSSFWorkbook(rowaccess);

            // 在workbook中添加一个sheet,对应Excel文件中的sheet
            // XSSFSheet sheet = workBook.createSheet("sheet1");
            Sheet sheet = workBook.createSheet();
            // ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            // XSSFCellStyle headStyle = exportUtil.getHeadStyle();//表头单元格样式
//			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();//表体单元格样式

            // 构建表头
            // XSSFRow headRow = sheet.createRow(0);
            Row rowheader = sheet.createRow(0);
            // 设置宽度（第一列 50个字符宽度）
            // sheet.setColumnWidth((short) 0, 50 * 256);
            // XSSFCell cell = null;
            Cell cellheader = null;
            for (int i = 0; i < titles.length; i++) {
                // cell = headRow.createCell(i);
                cellheader = rowheader.createCell(i);
                // cell.setCellStyle(headStyle);
                cellheader.setCellValue(titles[i]);
            }
            if (list != null && list.size() > 0) {
                for (int j = 0; j < list.size(); j++) {
                    // XSSFRow bodyRow = sheet.createRow(j + 1);
                    Row row = sheet.createRow(j + 1);
                    Map<String, String> goods = list.get(j);
                    for (int k = 0; k < keys.length; k++) {
                        Cell cell = row.createCell(k);
                        String cellvalueString = "";
                        if (goods.get(keys[k]) != null) {
                            cellvalueString = String.valueOf(goods.get(keys[k]));
                        }
                        if ("null".equals(cellvalueString) || "".equals(cellvalueString)) {
                            cell.setCellValue("");
                        } else {
                            Boolean isNum = false;// data是否为数值型
                            Boolean isNumKexue = false; // 科学计数法
                            Boolean isPercent = false;// data是否为百分数
                            if (cellvalueString != null || "".equals(cellvalueString)) {
                                // 判断data是否为数值型
                                isNum = cellvalueString.toString().matches("^(-?\\d+)(\\.\\d+)?$");

                                isNumKexue = cellvalueString.toString().matches("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$");
                                // 判断data是否为百分数（是否包含“%”）
                                isPercent = cellvalueString.toString().contains("%");
                            }
                            CellStyle contextstyle = workBook.createCellStyle();// .createCellStyle();
                            // 如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                            if ((isNum || isNumKexue) && !isPercent && "qty".equals(keys[k])
                                    || "res_qty".equals(keys[k]) || "amount".equals(keys[k])
                                    || "target_cost".equals(keys[k]) || "target_resale".equals(keys[k])
                                    || "sale_price".equals(keys[k]) || "stop_price".equals(keys[k])
                                    || "suggest_cost".equals(keys[k]) || "suggest_resale".equals(keys[k])) {
                                DataFormat df = workBook.createDataFormat();// .createDataFormat(); // 此处设置数据格式

                                if ("qty".equals(keys[k]) || "res_qty".equals(keys[k])) {
                                    contextstyle.setDataFormat(df.getFormat("#,###,###,###,##0"));// 数据格式只显示整数
                                    // 设置单元格格式
                                    cell.setCellStyle(contextstyle);
                                    BigDecimal bd = new BigDecimal(Double.valueOf(cellvalueString));

                                    // ss = String.valueOf(d1.format(Double.valueOf(cellvalueString))) ;
                                    cell.setCellValue(Double.parseDouble(bd.toPlainString()));
                                } else if ("target_cost".equals(keys[k]) || "target_resale".equals(keys[k])
                                        || "suggest_cost".equals(keys[k]) || "suggest_resale".equals(keys[k])
                                        || "sale_price".equals(keys[k]) || "stop_price".equals(keys[k])) {
                                    contextstyle.setDataFormat(df.getFormat("#,###,###,###,###,##0.0000"));// 数据格式只显示整数
                                    // 设置单元格格式
                                    cell.setCellStyle(contextstyle);
                                    // 设置单元格内容为double类型
                                    String ss = "";
                                    DecimalFormat d1 = new DecimalFormat("##########0.0000");
                                    ss = String.valueOf(d1.format(Double.valueOf(cellvalueString)));
                                    cell.setCellValue(Double.parseDouble(ss.toString()));
                                } else {
                                    contextstyle.setDataFormat(df.getFormat("#,###,###,###,###,##0.00"));// 保留两位小数点
                                    // 设置单元格格式
                                    cell.setCellStyle(contextstyle);
                                    // 设置单元格内容为double类型
                                    cell.setCellValue(Double.parseDouble(cellvalueString.toString()));
                                }

                            } else if ("qty".equals(keys[k])) {
                                DataFormat df1 = workBook.createDataFormat();
                                // 设置单元格格式
                                contextstyle.setDataFormat(df1.getFormat("#,###,###,###,##0"));
                                cell.setCellStyle(contextstyle);
                                BigDecimal bd = new BigDecimal(Double.valueOf(cellvalueString));

                                // ss = String.valueOf(d1.format(Double.valueOf(cellvalueString))) ;
                                cell.setCellValue(Double.parseDouble(bd.toPlainString()));
                            } else {
                                cell.setCellStyle(contextstyle);
                                // 设置单元格内容为字符型
                                cell.setCellValue(cellvalueString.toString());
                            }
                        }
                    }
                    // 每当行数达到设置的值就刷新数据到硬盘,以清理内存
                    if (j % rowaccess == 0) {
                        ((SXSSFSheet) sheet).flushRows();
                    }
                }
            }
            try {
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * 去掉字符串开头和结尾的字符 * @param stream 要处理的字符串
     * 
     * @param trimstr 要去掉的字符串
     * 
     * @param leftOrRight 去掉左边还是右边，或者2边 左边"l" 右边"r"
     * 
     * @return 处理后的字符串
     */
    public static String sideTrim(String stream, String trimstr, String leftOrRight) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if ("R".equals(leftOrRight)) {
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
            }
        } else if ("L".equals(leftOrRight)) { // 去掉开头的指定字符
            matcher = pattern.matcher(stream);
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = stream.substring(epos);
            }
        } else {
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
            }
            // 去掉开头的指定字符
            matcher = pattern.matcher(stream);
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = stream.substring(epos);
            }
        }

        // 返回处理后的字符串
        return stream;
    }

}
