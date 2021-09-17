package com.demo.xyz.common.core.util;

import com.demo.xyz.common.core.util.wrapper.UnZipFileInfo;
import com.demo.xyz.common.core.util.wrapper.UnZipImageFileInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	static final int BUFFER = 2048;

	public static final String KEY_MAP_IMAGE = "mapImage";
	public static final String KEY_MAP_FILE = "mapFile";

	public static List<UnZipFileInfo> unzip(String srcZip, String destDir) throws Exception {
		List<UnZipFileInfo> unZipFileInfos = new ArrayList<>();
		try {
			if (!destDir.endsWith("/")) {
				destDir = destDir + "/";
			}
			File destDirFile = new File(destDir);
			if (!destDirFile.exists()) {
				destDirFile.mkdir();
			}
			ZipFile zipFile = new ZipFile(srcZip, Charset.forName("GB2312"));
			Enumeration emu = zipFile.entries();
			int i = 0;
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				if (entry.isDirectory()) {
					new File(destDir + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
				File file = new File(destDir + entry.getName());
				// 加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
				// 而这个文件所在的目录还没有出现过，所以要建出目录来。
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);

				int count;
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					bos.write(data, 0, count);
				}

				// 获取文件信息
				UnZipFileInfo unZipFileInfo = new UnZipFileInfo(entry.getComment(), file.getAbsolutePath(),
						file.length());
				unZipFileInfos.add(unZipFileInfo);

				bos.flush();
				bos.close();
				bis.close();
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unzip fail:" + e.getMessage());
		}
		return unZipFileInfos;
	}

	@Deprecated
	public static List<UnZipImageFileInfo> unzipImages(String srcZip, String destDir) throws Exception {
		List<UnZipImageFileInfo> unZipFileInfos = new ArrayList<>();
		try {
			if (!destDir.endsWith("/")) {
				destDir = destDir + "/";
			}
			File destDirFile = new File(destDir);
			if (!destDirFile.exists()) {
				destDirFile.mkdir();
			}
			ZipFile zipFile = new ZipFile(srcZip, Charset.forName("GB2312"));
			Enumeration emu = zipFile.entries();
			int i = 0;
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				if (entry.isDirectory()) {
					new File(destDir + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
				File file = new File(destDir + entry.getName());
				// 加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
				// 而这个文件所在的目录还没有出现过，所以要建出目录来。
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}

				InputStream is = zipFile.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(file);

				int count;
				byte data[] = new byte[BUFFER];
				while ((count = is.read(data)) != -1) {
					fos.write(data, 0, count);
				}

				BufferedImage bi = ImageIO.read(file);

				// 获取文件信息
				UnZipImageFileInfo unZipFileInfo = new UnZipImageFileInfo();
				unZipFileInfo.setComment(entry.getComment());
				unZipFileInfo.setPath(file.getAbsolutePath());
				unZipFileInfo.setSize(file.length());
				// 因为包含有非地图图片文件，所以需要判断
				if (bi != null) {
					unZipFileInfo.setWidth(bi.getWidth());
					unZipFileInfo.setHeight(bi.getHeight());
				}
				unZipFileInfos.add(unZipFileInfo);

				fos.close();
				bis.close();
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unzip fail:" + e.getMessage());
		}
		return unZipFileInfos;
	}

	public static Map<String, List<UnZipImageFileInfo>> unzipMapFile(String srcZip, String destDir) throws Exception {

		Map<String, List<UnZipImageFileInfo>> mapFiles = new HashMap<>();

		try {
			if (!destDir.endsWith("/")) {
				destDir = destDir + "/";
			}
			File destDirFile = new File(destDir);
			if (!destDirFile.exists()) {
				destDirFile.mkdir();
			}
			ZipFile zipFile = new ZipFile(srcZip, Charset.forName("GB2312"));
			Enumeration emu = zipFile.entries();
			int i = 0;
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				if (entry.isDirectory()) {
					new File(destDir + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
				File file = new File(destDir + entry.getName());
				// 加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
				// 而这个文件所在的目录还没有出现过，所以要建出目录来。
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}

				InputStream is = zipFile.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(file);

				int count;
				byte data[] = new byte[BUFFER];
				while ((count = is.read(data)) != -1) {
					fos.write(data, 0, count);
				}

				BufferedImage bi = ImageIO.read(file);

				// 获取文件信息
				UnZipImageFileInfo unZipFileInfo = new UnZipImageFileInfo();
				unZipFileInfo.setComment(entry.getComment());
				unZipFileInfo.setPath(file.getAbsolutePath());
				unZipFileInfo.setSize(file.length());
				// 因为包含有非地图图片文件，所以需要判断
				if (bi != null) {
					List<UnZipImageFileInfo> unZipMapImageInfos = mapFiles.get(ZipUtil.KEY_MAP_IMAGE);
					if (unZipMapImageInfos == null) {
						unZipMapImageInfos = new ArrayList<>();
					}
					unZipFileInfo.setWidth(bi.getWidth());
					unZipFileInfo.setHeight(bi.getHeight());
					unZipMapImageInfos.add(unZipFileInfo);
					mapFiles.put(ZipUtil.KEY_MAP_IMAGE, unZipMapImageInfos);
				} else {
					// 地图描述文件
					List<UnZipImageFileInfo> unZipMapFileInfos = mapFiles.get(ZipUtil.KEY_MAP_FILE);
					if (unZipMapFileInfos == null) {
						unZipMapFileInfos = new ArrayList<>();
					}
					unZipMapFileInfos.add(unZipFileInfo);
					mapFiles.put(KEY_MAP_FILE, unZipMapFileInfos);
				}

				fos.close();
				bis.close();
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unzip fail:" + e.getMessage());
		}
		return mapFiles;
	}

	public void zip(String srcDir, String destZip) throws Exception {

		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(destZip);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			byte data[] = new byte[BUFFER];
			File f = new File(srcDir);
			File files[] = f.listFiles();

			for (int i = 0; i < files.length; i++) {
				FileInputStream fi = new FileInputStream(files[i]);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(files[i].getName());
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("zip fail:" + e.getMessage());
		}

	}

}