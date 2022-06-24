package com.jingtong.platform.country.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.common.CommonUtil;
import com.jingtong.platform.country.pojo.BranchMapping;
import com.jingtong.platform.country.pojo.Country;
import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.country.service.ICountryService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.JsonUtil;
import com.jingtong.platform.framework.util.StockUtil;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CountryAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private BranchMapping pr;

    public BranchMapping getPr() {
        return pr;
    }

    public void setPr(BranchMapping pr) {
        this.pr = pr;
    }

    private ICountryService countryService;
    private Country c;
    private SaleCountry sc;
    private List<Country> cList;
    private List<SaleCountry> scList;
    private String id;
    private int total;
    private String country_code;
    private String country_name;
    private String province_code;
    private String province_name;
    private String search;
    private String org_code;
    private String userId;
    private String saleCountryJson;
    private String uploadFile;
    private String disti_hq_soldto;
    private String region_level2;
    private String global_account;
    private String loc_rep_country;
    private List<BranchMapping> prList;

    private String path;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public List<BranchMapping> getPrList() {
        return prList;
    }

    public void setPrList(List<BranchMapping> prList) {
        this.prList = prList;
    }

    public String getDisti_hq_soldto() {
        return disti_hq_soldto;
    }

    public void setDisti_hq_soldto(String disti_hq_soldto) {
        this.disti_hq_soldto = disti_hq_soldto;
    }

    public String getRegion_level2() {
        return region_level2;
    }

    public void setRegion_level2(String region_level2) {
        this.region_level2 = region_level2;
    }

    public String getGlobal_account() {
        return global_account;
    }

    public void setGlobal_account(String global_account) {
        this.global_account = global_account;
    }

    public String getLoc_rep_country() {
        return loc_rep_country;
    }

    public void setLoc_rep_country(String loc_rep_country) {
        this.loc_rep_country = loc_rep_country;
    }

    /**
     * 跳转到查询页面
     * 
     * @return
     */
    public String tosearchCountry() {
        return "tosearchCountry";
    }

    /**
     * 跳转到查看页面
     * 
     * @return
     */
    public String toViewCountry() {
        c = new Country();
        c.setId(Long.valueOf(id));
        c = countryService.getCountryById(c);
        return "toViewCountry";
    }

    /**
     * 跳转到新增页面
     * 
     * @return
     */
    public String toCreateCountry() {
        c = new Country();
        return "toCreateCountry";
    }

    // 跳转到修改页面
    public String toUpdateCountry() {
        c = new Country();
        c.setId(Long.valueOf(id));
        c = countryService.getCountryById(c);
        return "toCreateCountry";
    }

    /**
     * 跳转到查询页面
     * 
     * @return
     */

    public String toSearchSaleCountry() {
        userId = userId;
        return "toSearchSaleCountry";
    }

    public String toSearchCountryProvince() {
        return "toSearchCountryProvince";
    }

    /**
     * 查询国家信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "cList", include = { "id", "country_code", "country_name", "org_code",
            "org_name" }, total = "total")
    public String getCountryList() {

        c = new Country();
        c.setStart(getStart());
        c.setEnd(getEnd());
        c.setSort("country_name");
        c.setDir("asc");
        if (StringUtils.isNotEmpty(country_code)) {
            c.setCountry_code(country_code.toUpperCase());
        }
        if (StringUtils.isNotEmpty(country_name)) {
            c.setCountry_name(country_name.toUpperCase());
        }
        if (StringUtils.isNotEmpty(search)) {
            c.setSearch(search.toUpperCase());
        }
        c.setOrg_code(org_code);
        total = countryService.getCountryListCount(c);
        if (total > 0) {
            cList = countryService.searchCountry(c);
        }
        return JSON;
    }

    /**
     * 添加信息
     * 
     * @return
     */
    public String addCountry() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        Country aa = new Country();
        aa.setCountry_code(c.getCountry_code());
        Boolean flag = checkCountryId(aa);
        if (flag) {
            long result = countryService.saveCountry(c);
            if (result > 0) {
                this.setSuccessMessage("Success,close current page？");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            this.setFailMessage("Code already exist!");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 修改信息
     */
    public String updateCountry() {

        this.setSuccessMessage("");
        this.setFailMessage("");
        Country aa = new Country();
        aa.setCountry_code(c.getCountry_code());
        if (aa.getCountry_code().equals(c.getOldCountry_code())) {
            long result = countryService.updateCountry(c);
            if (result > 0) {
                this.setSuccessMessage("Success,close current page？");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            Boolean flag = checkCountryId(aa);
            if (flag) {
                long result = countryService.updateCountry(c);
                if (result > 0) {
                    this.setSuccessMessage("Success,close current page？");
                } else {
                    this.setFailMessage("Failed!");
                }
            } else {
                this.setFailMessage("Code already exist!");
            }
        }

        return RESULT_MESSAGE;
    }

    /**
     * 删除信息
     * 
     * @return
     */
    public String delCountry() {

        this.setFailMessage("");
        this.setSuccessMessage("");
        c = new Country();
        c.setId(Long.valueOf(id));
        int i = countryService.deleteCountryById(c);
        if (i > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 验证该编码名是否被占用
     * 
     * @param g
     * @return
     */
    private Boolean checkCountryId(Country c) {
        Boolean b = true;
        int n = countryService.getCountByCode(c);
        if (n >= 1) {
            b = false;
        }
        return b;

    }

    /**
     * 查询销售对应的国家信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "scList", include = { "id", "country_code", "country_name", "org_code", "org_name", "userId",
            "userName", "userOrg", "province_code", "province_name" }, total = "total")
    public String searchSaleCountry() {

        sc = new SaleCountry();
        sc.setStart(getStart());
        sc.setEnd(getEnd());
        if (StringUtils.isNotEmpty(country_code)) {
            sc.setCountry_code(country_code.toUpperCase());
        }
        if (StringUtils.isNotEmpty(country_name)) {
            sc.setCountry_name(country_name.toUpperCase());
        }
        if (StringUtils.isNotEmpty(search)) {
            sc.setSearch(search.toUpperCase());
        }
        if (StringUtils.isNotEmpty(userId)) {
            sc.setUserId(userId);
        }
        // sc.setOrg_code(org_code);
        total = countryService.getSaleCountryListCount(sc);
        if (total > 0) {
            scList = countryService.searchSaleCountry(sc);
        }
        return JSON;
    }

    /**
     * 查询待分配的国家信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "cList", include = { "id", "country_code", "country_name", "org_code", "org_name",
            "province_code", "province_name" })
    public String searchCountryProvince() {

        c = new Country();
        if (StringUtils.isNotEmpty(country_code)) {
            c.setCountry_code(country_code.toUpperCase());
        }
        if (StringUtils.isNotEmpty(country_name)) {
            c.setCountry_name(country_name.toUpperCase());
        }
        if (StringUtils.isNotEmpty(org_code)) {
            c.setOrg_code(org_code);
        }
        if (StringUtils.isNotEmpty(search)) {
            c.setSearch(search.toUpperCase());
        }
        // c.setOrg_code(org_code);
        cList = countryService.searchCountryProvince(c);
        return JSON;
    }

    /**
     * 添加销售与国家的对应信息
     * 
     * @return
     */
    public String addSaleCountry() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        try {
            saleCountryJson = java.net.URLDecoder.decode(this.saleCountryJson, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        scList = JsonUtil.getDTOList(saleCountryJson, SaleCountry.class);

        BooleanResult bool = countryService.addSaleCountry(scList);
        if (bool.getResult()) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    public String delSaleCountry() {

        this.setFailMessage("");
        this.setSuccessMessage("");
        try {
            saleCountryJson = java.net.URLDecoder.decode(this.saleCountryJson, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        scList = JsonUtil.getDTOList(saleCountryJson, SaleCountry.class);

        BooleanResult bool = countryService.delSaleCountry(scList);
        if (bool.getResult()) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    // branch mapping table

    public String toSearchList() {
        return "toSearchList";
    }

    public String toCreateBranchMapping() {
        return "toCreateBranchMapping";
    }

    public String toUpdateBranchMapping() {
        pr = new BranchMapping();
        pr.setId(Long.valueOf(id));
        pr.setPage(1);
        pr.setStart(0);
        pr.setEnd(10);
        List<BranchMapping> pplis = countryService.getBranchMappingList(pr);
        if (pplis.size() > 0)
            pr = pplis.get(0);
        return "toUpdateBranchMapping";
    }

    /**
     * 查询所有信息
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "prList", include = { "id", "disti_hq_soldto", "region_level2", "global_account",
            "loc_rep_country", "create_time", "create_userid", "update_time", "update_userid" }, total = "total")
    public String getBranchMappingList() {
        try {
            pr = new BranchMapping();
            pr.setStart(getStart());
            pr.setEnd(getEnd());
            pr.setSort("create_time");
            pr.setDir("desc");

            if (StringUtils.isNotEmpty(disti_hq_soldto) && StringUtils.isNotEmpty(disti_hq_soldto.trim())) {
                try {
                    pr.setDisti_hq_soldto(disti_hq_soldto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(global_account) && StringUtils.isNotEmpty(global_account.trim())) {
                try {
                    pr.setGlobal_account(global_account);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(region_level2) && StringUtils.isNotEmpty(region_level2.trim())) {
                try {
                    pr.setRegion_level2(region_level2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotEmpty(loc_rep_country) && StringUtils.isNotEmpty(loc_rep_country.trim())) {
                try {
                    pr.setLoc_rep_country(loc_rep_country);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            total = countryService.getBranchMappingListCount(pr);
            if (total > 0) {
                prList = countryService.getBranchMappingList(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    /**
     * 添加信息
     * 
     * @return
     */
    public String createBranchMapping() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        pr.setCreate_userid(Long.valueOf(this.getUser().getUserId()));
        pr.setStart(0);
        pr.setEnd(100);
        prList = countryService.getBranchMappingList(pr);
        if (prList == null || prList.size() <= 0) {
            pr.setCreate_time(new Date());
            pr.setCreate_userid(Long.valueOf(this.getUser().getUserId()));
            long result = countryService.createBranchMapping(pr);
            if (result > 0) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage("Failed!");
            }
        } else {
            this.setFailMessage("Aready Exist!<br>");
        }
        return RESULT_MESSAGE;
    }

    /**
     * 添加信息
     * 
     * @return
     */
    public String updateBranchMapping() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        pr.setUpdate_userid(Long.valueOf(this.getUser().getUserId()));
        pr.setUpdate_time(new Date());
//    	BranchMapping aa = new BranchMapping(); 
//    	aa.setDisti_hq_soldto(disti_hq_soldto);
        int result = countryService.updateBranchMapping(pr);
        if (result > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    public String deleteBranchMapping() {
        this.setFailMessage("");
        this.setSuccessMessage("");
        String[] idss = ids.split(",");
        int i = 0;
        for (String id : idss) {
            pr = new BranchMapping();
            pr.setId(Long.valueOf(id));
            i = countryService.deleteBranchMapping(pr);
        }
        if (i > 0) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    public String importExcel() {
        return "importExcel";
    }

    /**
     * 导入excel数据
     * 
     * @return
     * @throws ParseException
     */
    public String findOrderExcelXls(String uploadFile) {
        // 导入数据
        FileInputStream fileIn = null;
        Workbook rwb = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        List<BranchMapping> pList = new ArrayList<BranchMapping>();
        StringBuilder contentResult = new StringBuilder();
        try {
            fileIn = new FileInputStream(uploadFile);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            /** 去除空行得到真实行数 **/
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                this.setFailMessage("导入的Excel为空！");
                return RESULT_MESSAGE;
            } else {

                for (int i = 1; i < actualRows; i++) {
                    if ("".equals(rs.getCell(0, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("HQ sold to is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(1, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("region level2 is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(2, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("global account is not completed yet!");
                        break;
                    }

                    if ("".equals(rs.getCell(3, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("Loc Rep country is not completed yet!");
                        break;
                    }

                }

                if (!"".equals(contentResult.toString())) {
                    this.setFailMessage(contentResult.toString());
                    return RESULT_MESSAGE;
                }
                BranchMapping prRule;
                for (int i = 1; i < actualRows; i++) {
                    prRule = new BranchMapping();
                    prRule.setDisti_hq_soldto(rs.getCell(0, i).getContents().trim());
                    prRule.setRegion_level2(rs.getCell(1, i).getContents().trim());
                    prRule.setGlobal_account(rs.getCell(2, i).getContents().trim());
                    prRule.setLoc_rep_country(rs.getCell(3, i).getContents().trim());
                    // System.out.println(rs.getCell(5, i).getContents().trim());
                    pList.add(prRule);
                }
            }
            long result = 1;
            for (BranchMapping branchMapping : pList) {
                int n = countryService.getBranchMappingListCount(branchMapping);
                if (n >= 1) {
                    branchMapping.setUpdate_time(new Date());
                    branchMapping.setUpdate_userid(Long.valueOf(this.getUser().getUserId()));
                    int a = countryService.updateBranchMapping(branchMapping);
                } else {
                    branchMapping.setCreate_time(new Date());
                    branchMapping.setCreate_userid(Long.valueOf(this.getUser().getUserId()));
                    result = countryService.createBranchMapping(branchMapping);
                }
                if (result == 0) {
                    break;
                }
            }
            if (result > 0) {
                this.setSuccessMessage("Success !");
            } else {
                this.setFailMessage("failed !");
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            this.setFailMessage("Excel模板错误！");
        } catch (BiffException e) {

            this.setFailMessage("03以上版本Excel导入暂不支持");
            return RESULT_MESSAGE;
        } catch (Exception e) {

            this.setFailMessage("failed (导入内容格式有误)！");
            return RESULT_MESSAGE;
        }
        return "Success";

    }

    private String findOrderExcelXlsx(String path) {
        StringBuilder contentResult = new StringBuilder();
        List<BranchMapping> pList = new ArrayList<BranchMapping>();
        BranchMapping prRule;
        try {

            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook xwb = new XSSFWorkbook(file);
            XSSFSheet sheet = xwb.getSheetAt(0);
            // 定义 row、cell
            XSSFRow row;
            // 循环输出表格中的内容
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                return "导入的Excel为空！";
            }
            System.out.println("表格总行数" + sheet.getPhysicalNumberOfRows());
            System.out.println("第一行行号" + sheet.getFirstRowNum());
            System.out.println("最后一行行号" + sheet.getLastRowNum());
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row == null) {// 空行跳过
                    continue;
                }
                prRule = new BranchMapping();

                XSSFCell cell0 = row.getCell(0);
                if (cell0 != null) {
                    if (cell0.getCellType() == cell0.CELL_TYPE_NUMERIC) {
                        // 数字格式无法解析
                        cell0.setCellType(cell0.CELL_TYPE_STRING);

                        prRule.setDisti_hq_soldto(cell0.getStringCellValue().trim());
                    } else {
                        prRule.setDisti_hq_soldto(cell0.getStringCellValue().trim());
                    }
                } else {
                    contentResult.append("Row " + i + ": Disti hq soldto is not completed yet!");
                    break;
                }

                XSSFCell cell1 = row.getCell(1);
                if (cell1 != null) {
                    if (cell1.getCellType() == cell1.CELL_TYPE_NUMERIC) {
                        // 数字格式无法解析
                        cell1.setCellType(cell1.CELL_TYPE_STRING);
                        prRule.setRegion_level2(cell1.getStringCellValue().trim());

                    } else {
                        prRule.setRegion_level2(cell1.getStringCellValue().trim());
                    }
                } else {
                    contentResult.append("Row " + i + ": Disti hq soldto is not completed yet!");
                    break;
                }

                XSSFCell cell2 = row.getCell(2);
                if (cell2 != null && !"".equals(cell2.getStringCellValue().trim())) {
                    prRule.setGlobal_account(cell2.getStringCellValue().trim());
                } else {
                    contentResult.append("Row " + i + ": Global account is not completed yet!");
                    break;
                }

                XSSFCell cell3 = row.getCell(3);
                if (cell3 != null && !"".equals(cell3.getStringCellValue().trim())) {
                    prRule.setLoc_rep_country(cell3.getStringCellValue().trim());
                } else {
                    contentResult.append("Row " + i + ": Loc Rep Country is not completed yet!");
                    break;
                }
                prRule.setCreate_userid(Long.valueOf(this.getUser().getUserId().toString()));
                pList.add(prRule);
            }
            if (!"".equals(contentResult.toString())) {
                return contentResult.toString();
            }
            long result = 1;
            for (BranchMapping branchMapping : pList) {
                int n = countryService.getBranchMappingListCount(branchMapping);
                if (n >= 1) {
                    branchMapping.setUpdate_time(new Date());
                    branchMapping.setUpdate_userid(Long.valueOf(this.getUser().getUserId()));
                    int a = countryService.updateBranchMapping(branchMapping);
                } else {
                    branchMapping.setCreate_time(new Date());
                    branchMapping.setCreate_userid(Long.valueOf(this.getUser().getUserId()));
                    result = countryService.createBranchMapping(branchMapping);
                }
                if (result == 0) {
                    break;
                }
            }
            file.close();
            if (result > 0) {
                return "Success";
            } else {
                return "Failed";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "failed (导入内容格式有误)！";
        }
    }

    public String findOrderExcel() {
        String result;
        if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
            result = findOrderExcelXls(uploadFile);
            if ("Success".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else if (ExcelUtil.getExcelStyle(path).intValue() == 2) {
            result = findOrderExcelXlsx(uploadFile);
            if ("Success".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else {
            this.setFailMessage("failed (导入内容格式有误)！");
            return RESULT_MESSAGE;
        }
    }

    public void downloadExcelForBranchMapping() throws Exception {
        try {
            this.setPage(1);
            this.setRows(1000000);
            // 查询数据
            getBranchMappingList();

            Date aDate = new Date();
            SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateString = aDateFormat.format(aDate);
            String fileName = "branch Mapping" + dateString;

            CommonUtil.setExcelResponseInfo(getServletRequest(), getServletResponse(), fileName);
            // excel表头
            String[] titles = { "DISTI HQ SOLDTO", "Region Level2", "Global Account", "Loc Rep Country" };
            // 表头对应list中Map对应属性名称
            String[] keys = { "disti_hq_soldto", "region_level2", "global_account", "loc_rep_country" };
            List<Map<String, String>> li = new ArrayList<Map<String, String>>();
            for (BranchMapping p : prList) {
                li.add(CommonUtil.transBean2Map(p));
            }
            CommonUtil.exportExcel(li, titles, keys, getServletResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public ICountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(ICountryService countryService) {
        this.countryService = countryService;
    }

    public Country getC() {
        return c;
    }

    public void setC(Country c) {
        this.c = c;
    }

    public List<Country> getcList() {
        return cList;
    }

    public void setcList(List<Country> cList) {
        this.cList = cList;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public SaleCountry getSc() {
        return sc;
    }

    public void setSc(SaleCountry sc) {
        this.sc = sc;
    }

    public List<SaleCountry> getScList() {
        return scList;
    }

    public void setScList(List<SaleCountry> scList) {
        this.scList = scList;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getSaleCountryJson() {
        return saleCountryJson;
    }

    public void setSaleCountryJson(String saleCountryJson) {
        this.saleCountryJson = saleCountryJson;
    }
}
