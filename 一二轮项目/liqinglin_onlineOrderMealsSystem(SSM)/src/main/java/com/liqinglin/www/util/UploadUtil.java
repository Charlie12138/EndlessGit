package com.liqinglin.www.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.serviceImp.StoreServiceImp;

public class UploadUtil {


	/**
	 *  上传文件
	 */
	@SuppressWarnings({ "unchecked" })
	public static Cuisine upload(HttpServletRequest request) {
		StoreServiceImp service = new StoreServiceImp();
		Cuisine cuisine = (Cuisine)request.getSession().getAttribute("cuisine");
		if (cuisine == null) {
			cuisine = new Cuisine();
		}
		if (ServletFileUpload.isMultipartContent(request)) {
			// 构造工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 设置缓冲区大小和临时目录
			factory.setSizeThreshold(1024 * 1024 * 8);// 8M 临时缓冲区（上传文件不大于8M不会产生临时文件）
			
			File repository = new File(request.getSession().getServletContext().getRealPath("/picture/temp"));//文件超过8M后会上产生临时文件，在这个路径
			
			factory.setRepository(repository);// 当上传文件超过8M 会在临时目录中产生临时文件

			// 获得解析器
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 解决上传文件名 乱码问题
			upload.setHeaderEncoding("UTF-8");
			// 限制上传文件大小
			upload.setFileSizeMax(1024 * 1024 * 72);
			try {
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem fileItem : list) {
					// 判断每个FileItem 是不是文件上传项
					if (fileItem.isFormField()) {
						try {
							// 不是上传文件
							String name = fileItem.getFieldName(); // name属性
							String value = fileItem.getString("UTF-8"); // value 属性
							if(name.equals("cuisineName")) {
								cuisine.setCuisineName(value);
							} else if(name.equals("price")) {
								cuisine.setPrice(Double.parseDouble(value));
							} else if(name.equals("description")) {
								cuisine.setDescription(value);
							} else if(name.equals("id")) {
								cuisine.setId(Integer.parseInt(value));
							} 
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else {
						String filename = fileItem.getName(); // 文件名

						// 判断是否为空(没有上传图片)
						if (filename == "") {
							// 设置美食图片
							cuisine.setPicturePath(filename);
							return cuisine;
						}

						// 判断图片后缀名格式是否正确
						if (service.imageFormatIsRight(filename) == Contants.IMAGE_FORMAT_IS_ERROR_CODE) {
							return cuisine;
						}

						//解决老版本浏览器IE6 文件路径存在问题
						if (filename.contains("\\")) {
							filename = filename.substring(filename.lastIndexOf("\\") + 1);// 获取图片名字最后的格式
						}

						InputStream in = null;
						try {
							in = new BufferedInputStream(fileItem.getInputStream());
						} catch (IOException e) {
							e.printStackTrace();
						}

						// 保证上传文件名唯一
						filename = UUID.randomUUID().toString() + filename;
						String path = request.getSession().getServletContext().getRealPath("/picture/upload");
						path = path.replace("\\", "/");
						File file = new File(path);
						String pathName = file.toString().replace("\\", "/") + "/" + filename;
						//int indexOf(String str)返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
						String picturePath = pathName.substring(pathName.indexOf("/picture"));// 获得商品图片所在目录
						file.mkdirs();
						cuisine.setPicturePath(picturePath);
						// 将文件内容输出web/picture/upload 目录
						File targetFile = new File(file, filename);// 创建路径是path，名字是filename的文件。
						OutputStream out = null;
						try {
							out = new BufferedOutputStream(new FileOutputStream(targetFile));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						int temp;

						try {
							while ((temp = in.read()) != -1) {
								out.write(temp);
							}
							out.close();
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						// 删除临时文件(必须将文件流关掉)
						fileItem.delete();
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		return cuisine;
	}
}
