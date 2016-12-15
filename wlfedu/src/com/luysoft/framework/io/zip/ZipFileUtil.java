package com.luysoft.framework.io.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipFileUtil {

	/**
	 * 将指定文件压缩到字节流中。
	 * 注意，如果文件较大会导致内存溢出，谨慎使用
	 * 
	 * @param entryFiles 压缩文件列表
	 * @param entryNames 压缩文件名列表
	 * 
	 * @throws IOException
	 */
	public static ByteArrayOutputStream createZipStream(List<File> entryFiles, List<String> entryNames)
			throws IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeZipStream(bos, entryFiles, entryNames);

		return bos;
	}

	/**
	 * 将指定文件压缩到目标Zip文件
	 * 
	 * @param zipFile 目标文件
	 * @param entryFiles 压缩文件列表
	 * @param entryNames 压缩文件名列表
	 * 
	 * @throws IOException
	 */
	public static void createZipFile(File zipFile, List<File> entryFiles, List<String> entryNames) throws IOException {
		writeZipStream(new FileOutputStream(zipFile), entryFiles, entryNames);
	}

	/**
	 * 将指定文件压缩到指定的输出流
	 * 
	 * @param out 输出流
	 * @param entryFiles 压缩文件列表
	 * @param entryNames 压缩文件名列表
	 * 
	 * @throws IOException 
	 */
	public static void writeZipStream(OutputStream out, List<File> entryFiles, List<String> entryNames) throws IOException {

		ZipOutputStream zos = new ZipOutputStream(out);
		zos.setEncoding(System.getProperty("sun.jnu.encoding")); 
		for (int i=0; i<entryFiles.size(); i++) {
			addZipEntry(zos, entryFiles.get(i), entryNames.get(i));
			zos.flush();
		}
		zos.close();
	}

	// 添加压缩文件。
	private static void addZipEntry(ZipOutputStream out, File targetFile, String entryName) throws IOException {

		ZipEntry target = new ZipEntry(entryName);
		out.putNextEntry(target);

		byte buf[] = new byte[1024];
		int count;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(targetFile));
		while ((count = in.read(buf, 0, 1024)) != -1) {
			out.write(buf, 0, count);
		}
		in.close();
		out.closeEntry();
		in = null;
	}
}
