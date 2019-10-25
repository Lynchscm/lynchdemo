package com.bosssoft.producer.contorller;

import com.bosssoft.producer.pojo.Entity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * @author Lynch
 * @date 2019/10/11 -20:01
 */
@RestController
@Api(value = "测试接口")
public class TestController {

//	@Resource
//	private Producer producer;
//	@Resource
//	private Cusumer consumer;

	@ApiOperation(value="获取用户列表", notes="获取用户列表")
	@RequestMapping(value = "test", method = RequestMethod.POST)
	@CrossOrigin
	public String test(@RequestBody Entity entity){
//		producer.pro();
//		consumer.cus();
		System.out.println(entity.toString());
		return entity.toString();
	}

//	/**
//	 * itext截取pdf
//	 * 截取pdfFile的第from页至第end页，组成一个新的文件名
//	 * @param respdfFile  需要分割的PDF
//	 * @param savepath  新PDF
//	 * @param from  起始页
//	 * @param end  结束页
//	 */
//	public static void splitPDFFile(String respdfFile, String savepath, int from, int end) throws BadPdfFormatException {
//		Document document = null;
//		PdfCopy copy = null;
//		try {
//			PdfReader reader = new PdfReader(respdfFile);
//			int n = reader.getNumberOfPages();
//			if(end==0){
//				end = n;
//			}
//			ArrayList<String> savepaths = new ArrayList<String>();
//			String staticpath = respdfFile.substring(0, respdfFile.lastIndexOf("\\")+1);
//			//String savepath = staticpath+ newFile;
//			savepaths.add(savepath);
//			document = new Document(reader.getPageSize(1));
//			copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
//			document.open();
//			for(int j=from; j<=end; j++) {
//				document.newPage();
//				PdfImportedPage page = copy.getImportedPage(reader, j);
//				copy.addPage(page);
//			}
//			document.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@RequestMapping(value = "postPdf", method = RequestMethod.POST)
	@CrossOrigin
	public FileInputStream dotest(@RequestBody Entity entity) throws FileNotFoundException {
		//String pdfFilePath,String depositPath,int totalPage,String pgStartPos,int startPage, int pageNum
//		String pdfFilePath="C:/Mapper.pdf";
//		String depositPath="C:/MapperTEST.pdf";
//		splitPDFFile(pdfFilePath,depositPath,2,3);
		return pdfOfSplit(entity.getStartPage(), entity.getEndPage());
	}
	/**
	 * pdfbox截取
	 */
	private FileInputStream pdfOfSplit(int startPage, int endPage){
		try {
			String resourcePath = "C:/FreeMarker中文手册.pdf";
			File resource = new File(resourcePath);
			//加载文档
			PDDocument doc = PDDocument.load(resource);
			PDPageTree pages = doc.getDocumentCatalog().getPages();
			//切割文档
			Iterator<PDPage> it=pages.iterator();
			int i = 1;
			PDDocument pdDocument  = new PDDocument();
			while (it.hasNext()){
				if (i >= startPage){
					pdDocument.addPage(it.next());
				}
				if (i >endPage){
					break;
				}
				i++;
				it.next();
			}
			pdDocument.save("C:/FreeMarker中文手册123.pdf");
			pdDocument.close();
			return new FileInputStream(new File(String.valueOf(pdDocument)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


//	/**
//	 * 阅读PDF文件的内容，支持阅读中文(如果需要阅读指定页面的PDF自己改写此方法)
//	 *
//	 * @throws IOException
//	 * @throws InvalidPasswordException
//	 */
//	@RequestMapping(value = "pdf", method = RequestMethod.GET)
//	public static void readPdfText() throws IOException, InvalidPasswordException {
//		try (PDDocument document = PDDocument.load(new File("C:/Mapper.pdf"))) {
//			AccessPermission ap = document.getCurrentAccessPermission();
//			if (!ap.canExtractContent()) {
//				throw new IOException("You do not have permission to extract text");
//			}
//
//			PDFTextStripper stripper = new PDFTextStripper();
//
//			stripper.setSortByPosition(true);
//
//			for (int p = 1; p <= document.getNumberOfPages(); ++p) {
//				stripper.setStartPage(p);
//				stripper.setEndPage(p);
//
//				String text = stripper.getText(document);
//
//				String pageStr = String.format("page %d:", p);
//				System.out.println(pageStr);
//				for (int i = 0; i < pageStr.length(); ++i) {
//					System.out.print("-");
//				}
//				System.out.println();
//				System.out.println(text.trim());
//				System.out.println();
//			}
//		}
//	}

}
