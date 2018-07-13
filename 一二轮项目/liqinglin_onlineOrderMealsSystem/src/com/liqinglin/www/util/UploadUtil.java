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

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;



import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.StoreService;

public class UploadUtil {

	
	/**
	 *  上传文件
	 */
	@SuppressWarnings({ "unchecked" })
	public static Cuisine upload(HttpServletRequest request) {
		Cuisine cuisine = new Cuisine();
		StoreService service = new StoreService();
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
							// TODO Auto-generated catch block
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
						try {
							if (service.imageFormatIsRight(filename) == Contants.IMAGE_FORMAT_IS_ERROR_CODE) {
																								
								return cuisine;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						// 符合条件
						// 解决老版本浏览器IE6 文件路径存在问题
						if (filename.contains("\\")) {
							filename = filename.substring(filename.lastIndexOf("\\") + 1);// 获取图片名字最后的格式
						}
						InputStream in = null;
						try {
							in = new BufferedInputStream(fileItem.getInputStream());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // 文件内容

						// 保证上传文件名唯一
						filename = UUID.randomUUID().toString() + filename;
						
						File path = new File(request.getSession().getServletContext().getRealPath("/picture/upload"));
						
						String pathName = path.toString().replace("\\", "/") + "/" + filename;

						String picturePath = pathName.substring(pathName.indexOf("/liqinglin_onlineOrderMealsSystem"));// 获得商品图片所在目录

						path.mkdirs();

						cuisine.setPicturePath(picturePath);
						
						// 将文件内容输出WEB-INF/upload 目录
						File targetFile = new File(path, filename);// 创建路径是path，名字是filename的文件。

						OutputStream out = null;
						try {
							out = new BufferedOutputStream(new FileOutputStream(targetFile));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// 删除临时文件(必须将文件流关掉)
						fileItem.delete();
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return cuisine;
	}
}
