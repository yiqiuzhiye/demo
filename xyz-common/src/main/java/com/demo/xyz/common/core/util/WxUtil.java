package com.demo.xyz.common.core.util;

import com.demo.xyz.common.core.constant.WXPayConstants;
import com.demo.xyz.common.core.constant.WXPayConstants.SignType;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Slf4j
public class WxUtil {

	private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final Random RANDOM = new SecureRandom();

	public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
		documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
		documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		documentBuilderFactory.setXIncludeAware(false);
		documentBuilderFactory.setExpandEntityReferences(false);

		return documentBuilderFactory.newDocumentBuilder();
	}

	public static Document newDocument() throws ParserConfigurationException {
		return newDocumentBuilder().newDocument();
	}

	/**
	 * 生成微信随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getNonceStr(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String getNonceStr() {
		char[] nonceChars = new char[32];
		for (int index = 0; index < nonceChars.length; ++index) {
			nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
		}
		return new String(nonceChars);
	}

	/**
	 * 生成签名
	 * 
	 * @param signMap
	 * @param signKey
	 * @return
	 */
	public static String sign(Map<String, Object> signMap, String signKey) {

		// 组装md5签名字符串
		String sign = "";
		String[] sortKeyArr = signMap.keySet().toArray(new String[0]);
		Arrays.sort(sortKeyArr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sortKeyArr.length; i++) {

			if (i > 0) {
				sb.append("&");
			}
			sb.append(sortKeyArr[i]);
			sb.append("=");
			sb.append(signMap.get(sortKeyArr[i]));
		}
		sb.append("&key=" + signKey);
		System.out.println(">>>sign url:" + sb.toString());
		return EncryptUtils.encryptMD5(sb.toString()).toUpperCase();

	}

	public static String sign2(Map<String, Object> signMap, String signKey) {

		// 组装md5签名字符串
		String sign = "";
		String[] sortKeyArr = signMap.keySet().toArray(new String[0]);
		Arrays.sort(sortKeyArr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sortKeyArr.length; i++) {

			if (i > 0) {
				sb.append("&");
			}
			sb.append(sortKeyArr[i]);
			sb.append("=");
			sb.append(signMap.get(sortKeyArr[i]));
		}
		sb.append("&key=" + signKey);
		System.out.println(">>>sign url:" + sb.toString());
		try {
			return HMACSHA256(sb.toString(), signKey).toUpperCase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 生成 HMACSHA256
	 * 
	 * @param data 待处理数据
	 * @param key  密钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String HMACSHA256(String data, String key) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * XML格式字符串转换为Map
	 *
	 * @param strXML XML字符串
	 * @return XML数据转换后的Map
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(String strXML) throws Exception {
		try {
			Map<String, String> data = new HashMap<String, String>();
			DocumentBuilder documentBuilder = newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
			Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			try {
				stream.close();
			} catch (Exception ex) {
				// do nothing
			}
			return data;
		} catch (Exception ex) {
			log.error("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(),
					strXML);
			throw ex;
		}

	}

	/**
	 * 将Map转换为XML格式的字符串
	 *
	 * @param data Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String mapToXml(Map<String, String> data) throws Exception {
		Document document = newDocument();
		org.w3c.dom.Element root = document.createElement("xml");
		document.appendChild(root);
		for (String key : data.keySet()) {
			String value = data.get(key);
			if (value == null) {
				value = "";
			}
			value = value.trim();
			org.w3c.dom.Element filed = document.createElement(key);
			filed.appendChild(document.createTextNode(value));
			root.appendChild(filed);
		}
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);
		String output = writer.getBuffer().toString(); // .replaceAll("\n|\r", "");
		try {
			writer.close();
		} catch (Exception ex) {
		}
		return output;
	}

	/**
	 * 生成带有 sign 的 XML 格式字符串
	 *
	 * @param data Map类型数据
	 * @param key  API密钥
	 * @return 含有sign字段的XML
	 */
	public static String generateSignedXml(final Map<String, String> data, String key) throws Exception {
		return generateSignedXml(data, key, SignType.MD5);
	}

	/**
	 * 生成带有 sign 的 XML 格式字符串
	 *
	 * @param data     Map类型数据
	 * @param key      API密钥
	 * @param signType 签名类型
	 * @return 含有sign字段的XML
	 */
	public static String generateSignedXml(final Map<String, String> data, String key, SignType signType)
			throws Exception {
		String sign = generateSignature(data, key, signType);
		data.put(WXPayConstants.FIELD_SIGN, sign);
		return mapToXml(data);
	}

	/**
	 * 判断签名是否正确
	 *
	 * @param xmlStr XML格式数据
	 * @param key    API密钥
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
		Map<String, String> data = xmlToMap(xmlStr);
		if (!data.containsKey(WXPayConstants.FIELD_SIGN)) {
			return false;
		}
		String sign = data.get(WXPayConstants.FIELD_SIGN);
		return generateSignature(data, key).equals(sign);
	}

	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
	 *
	 * @param data Map类型数据
	 * @param key  API密钥
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
		return isSignatureValid(data, key, SignType.MD5);
	}

	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。
	 *
	 * @param data     Map类型数据
	 * @param key      API密钥
	 * @param signType 签名方式
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
		if (!data.containsKey(WXPayConstants.FIELD_SIGN)) {
			return false;
		}
		String sign = data.get(WXPayConstants.FIELD_SIGN);
		return generateSignature(data, key, signType).equals(sign);
	}

	/**
	 * 生成签名
	 *
	 * @param data 待签名数据
	 * @param key  API密钥
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, String> data, String key) throws Exception {
		return generateSignature(data, key, SignType.MD5);
	}

	/**
	 * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
	 *
	 * @param data     待签名数据
	 * @param key      API密钥
	 * @param signType 签名方式
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, String> data, String key, SignType signType)
			throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if (k.equals(WXPayConstants.FIELD_SIGN)) {
				continue;
			}
			if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append("=").append(data.get(k).trim()).append("&");
		}
		sb.append("key=").append(key);
		if (SignType.MD5.equals(signType)) {
			return MD5(sb.toString()).toUpperCase();
		} else if (SignType.HMACSHA256.equals(signType)) {
			return HMACSHA256(sb.toString(), key);
		} else {
			throw new Exception(String.format("Invalid sign_type: %s", signType));
		}
	}

	/**
	 * 生成 MD5
	 *
	 * @param data 待处理数据
	 * @return MD5结果
	 */
	public static String MD5(String data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成证书
	 *
	 * @param privateKey
	 * @param mchId
	 * @param mchSerialNo
	 * @param apiV3Key
	 * @return
	 */
	public static AutoUpdateCertificatesVerifier generateVerifier(PrivateKey privateKey, String mchId, String mchSerialNo, String apiV3Key){
		try{
			AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
					new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, privateKey)),
					apiV3Key.getBytes("utf-8"));
			return verifier;
		}catch (UnsupportedEncodingException e) {
			throw new RuntimeException("apiV3Key转换失败");
		}
	}

	/**
	 * 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签，并进行证书自动更新
	 *
	 * @param privateKeyStr
	 * @param mchId
	 * @param mchSerialNo
	 * @param mchAPlv3Key
	 * @return
	 */

	public static HttpClient getWxHttpClient(String privateKeyStr, String mchId, String mchSerialNo, String mchAPlv3Key) throws UnsupportedEncodingException {
		PrivateKey privateKey = PemUtil.loadPrivateKey(
				new ByteArrayInputStream(privateKeyStr.getBytes("utf-8")));
		AutoUpdateCertificatesVerifier verifier = generateVerifier(privateKey, mchId, mchSerialNo, mchAPlv3Key);
		return WechatPayHttpClientBuilder.create()
				.withMerchant(mchId, mchSerialNo, privateKey)
				.withValidator(new WechatPay2Validator(verifier)).build();
	}

	public static String payScoreNotifyDecrypt(String wxPayScoreReqVo, String privateKeyStr, String mchId, String mchSerialNo, String mchApiKey) throws IllegalBlockSizeException{
		try{
			//PrivateKey privateKey = generatePrivateKey(new ByteArrayInputStream(privateKeyStr.getBytes("utf-8")));
			PrivateKey privateKey = PemUtil.loadPrivateKey(
					new ByteArrayInputStream(privateKeyStr.getBytes("utf-8")));
			AutoUpdateCertificatesVerifier verifier = WxUtil.generateVerifier(privateKey, mchId, mchSerialNo, mchApiKey);
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, verifier.getValidCertificate().getPublicKey());
			byte[] data = wxPayScoreReqVo.getBytes(StandardCharsets.UTF_8);
			byte[] ciphertext = cipher.doFinal(data);
			return java.util.Base64.getEncoder().encodeToString(ciphertext);
		}catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new RuntimeException("当前Java环境不支持RSA v1.5/OAEP", e);
		} catch (InvalidKeyException e) {
			throw new IllegalArgumentException("无效的证书", e);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new IllegalBlockSizeException("加密原串的长度不能超过214字节");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalBlockSizeException("获取秘钥失败");
		}
	}

}
