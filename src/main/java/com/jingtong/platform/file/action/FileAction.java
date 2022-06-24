package com.jingtong.platform.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.file.pojo.BudgetFileTmp;
import com.jingtong.platform.file.service.IFileService;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.FileUtil;

public class FileAction extends BaseAction {

	private static final long serialVersionUID = -6418680336897962924L;

	private IFileService fileService;
	
	private String wfeDownloadPath;
	private String imgUploadPath;
	private String imgUploadPathUrl;
	private String fileId;
	private String file_name;
	private String file_path;
	private File upload;
	private String uploadFileName;
	// 下载路径
	private String downloadFromPath;
	// excel模板
	@Decode
	private String excelModel;
	
	public String toImportFile(){
		return "toImportFile";
	}

	@PermissionSearch
	public String downLoadFile() {
//		BudgetFileTmp fileTmp = fileService.getFileByFileId(Long
//				.parseLong(fileId.trim()));
		//File source = new File(wfeDownloadPath + "/" + fileTmp.getSubFolders()+ "/" + fileTmp.getFileNameNew());
		File source = new File(file_path);
		System.out.println("file_path:"+file_path+"    isExist?"+source.exists());
		if(source.exists()){
			display(source,file_path.substring(file_path.lastIndexOf("/")),
					ServletActionContext.getResponse());
		}else{
			this.setFailMessage("文件不存在!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	@PermissionSearch
	private boolean display(File file, String fileName,
			HttpServletResponse response) {

		FileInputStream in = null;
		OutputStream out = null;
		try {

			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
				}
			}
			return true;
		}
	}
	
	@PermissionSearch
	public String uploadImagPrepare(){
		return "uploadImagPrepare";
	}
	
	/**
	 * 上传图片
	 * @return
	 */
	@PermissionSearch
	public String uploadImage(){
		// 重命名
		String newFileName = null;
		boolean saveAsFile = false;
		if(uploadFileName!=null && uploadFileName.length()>0){
			
//			String t=DateUtil.datetime("yyyyMM");
			String savedirPath = imgUploadPath ;
			String rpath=imgUploadPathUrl ;
			System.out.println("savedirPath："+imgUploadPath);
			System.out.println("rpath："+imgUploadPathUrl);
			System.out.println("uploadFileName："+uploadFileName);
			File savedir = new File(savedirPath);
			// 如果目录不存在，则新建
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			newFileName = String.valueOf(new Date().getTime()) + FileUtil.getFileExtention(uploadFileName);
			File targetFile = new File(savedirPath + "/" + newFileName);
			try {
				saveAsFile = FileUtil.saveAsFile1(upload, targetFile);
			} catch (Exception e) {
				e.printStackTrace();
				this.setFailMessage("文件上传失败");
				return RESULT_MESSAGE;
			}
			
			System.out.println("Success?："+saveAsFile);
			if (saveAsFile) {
				this.setSuccessMessage(rpath + "/" + newFileName);
			}else{
				this.setFailMessage("文件上传失败");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	
	
	
	// 导出Excel模板
	@SuppressWarnings("unused")
	public void downloadExcelModel() {
		try {
			System.out.println("downloadFromPath"+downloadFromPath);
			// 从配置文件读到的文件路径字符串
			String fileStr = new String(downloadFromPath.getBytes(), "GBK") + new String(excelModel.getBytes(), "GBK");
			// 磁盘路径
			String filepath = this.getClass().getResource(fileStr).toURI().getPath();
			File source = new File(filepath);
			System.out.println("filepath"+filepath);
			if (!source.exists()) {
				source.mkdirs();
			}
			if (source != null) {
				if("InStockExcel".equals(excelModel.substring(0, excelModel.indexOf(".")))){
					display(source, "入库导入" + "模板.xls",
							ServletActionContext.getResponse());
				}else if("WeEn POS".equals(excelModel.substring(0, excelModel.indexOf(".")))){
					display(source, excelModel.substring(0, excelModel.indexOf(".")) + ".xlsx",
							ServletActionContext.getResponse());
				}else if("Claim".equals(excelModel.substring(0, excelModel.indexOf(".")))){
					display(source, excelModel.substring(0, excelModel.indexOf(".")) + ".xlsx",
							ServletActionContext.getResponse());
				}else{
					display(source, excelModel.substring(0, excelModel.indexOf(".")) + ".xls",
							ServletActionContext.getResponse());
				}
				
			} else {
				this.setFailMessage(excelModel.substring(0, excelModel.indexOf(".")) + "模板导出出错");
			}
		} catch (Exception e) {
			this.setFailMessage(excelModel.substring(0, excelModel.indexOf(".")) + "模板导出出错");
		}
	}
	
	public IFileService getFileService() {
		return fileService;
	}

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	public String getWfeDownloadPath() {
		return wfeDownloadPath;
	}

	public void setWfeDownloadPath(String wfeDownloadPath) {
		this.wfeDownloadPath = wfeDownloadPath;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getImgUploadPath() {
		return imgUploadPath;
	}

	public void setImgUploadPath(String imgUploadPath) {
		this.imgUploadPath = imgUploadPath;
	}

	public String getImgUploadPathUrl() {
		return imgUploadPathUrl;
	}

	public void setImgUploadPathUrl(String imgUploadPathUrl) {
		this.imgUploadPathUrl = imgUploadPathUrl;
	}

	public String getDownloadFromPath() {
		return downloadFromPath;
	}

	public void setDownloadFromPath(String downloadFromPath) {
		this.downloadFromPath = downloadFromPath;
	}

	public String getExcelModel() {
		return excelModel;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public void setExcelModel(String excelModel) {
		this.excelModel = excelModel;
	}
    
	
}
