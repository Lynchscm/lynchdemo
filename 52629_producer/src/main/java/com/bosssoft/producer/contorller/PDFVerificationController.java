package com.bosssoft.producer.contorller;

import electronicseal.SealDetails;
import org.springframework.web.bind.annotation.*;
import com.bosssoft.producer.pojo.PDFVerificationVO;

/**
 * @ProjectName: 52629
 * @Package: controller
 * @ClassName: PDFVerificationController
 * @Author: Lynch
 * @Description: 对PDF文件进行验签，获取验签结果
 * @Date: 2019-10-24 9:45
 * @Version: 1.0
 */
@RestController
public class PDFVerificationController {
	/**
	 * 测试授权码
	 */
	private final String SYSID = "270ee980476111e8ab32005056a72395";
	/**
	 * 福建瑞术授权码控制服务器测试地址
	 */
	private final String FJRSURL = "http://114.115.172.176:8080/AuthorizationCode/authorization";

	/**
	 * 测试授权区域
	 */
	private final String UNIT = "FJRS";

	@RequestMapping(value = "/verification", method = RequestMethod.POST)
	public String pdfVerification(@RequestBody PDFVerificationVO pdfVerificationVO){
		if (pdfVerificationVO.getFileInputPath() == null || "".equals(pdfVerificationVO.getFileInputPath())){
			System.out.println("抛出异常");
		}
		return SealDetails.verifySignToBoss(pdfVerificationVO.getFileInputPath(), pdfVerificationVO.getFilePassword().getBytes(), SYSID, FJRSURL);
	}


//	@RequestMapping(value = "/verification", method = RequestMethod.GET)
//	public String pdfVerification(){
//		PDFVerificationVO pdfVerificationVO = new PDFVerificationVO();
//		pdfVerificationVO.setFileInputPath("C:/Netty.pdf");
//		pdfVerificationVO.setFilePassword("".getBytes());
//		if (pdfVerificationVO.getFileInputPath() == null || "".equals(pdfVerificationVO.getFileInputPath())){
//			System.out.println("抛出异常");
//		}
//		System.out.println(pdfVerificationVO);
//
//		return SealDetails.verifySignToBoss(pdfVerificationVO.getFileInputPath(), pdfVerificationVO.getFilePassword(), SYSID, FJRSURL);
//	}
}
