package com.course.httpclient.demo;

import com.course.httpclient.HttpUtils;
import com.course.httpclient.Md5Utils;
import com.course.httpclient.bean.LoginBean;
import com.course.httpclient.bean.WorkReportBean;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.Md5Crypt;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QaZhongTai {

//    @Test
//    public void testLoginAndAdd() throws Exception{
//        LoginBean loginBean = login();
//
//        if(200 == loginBean.getHeads().getCode()){
//            //设置token
//            HttpUtils.token = loginBean.getBody().getAccess_token();
//
//            System.out.println("登录成功:"+loginBean.getBody().getRealName());
//
//            //新增工单
//            System.out.println("新增工单开始");
//            WorkReportBean addWorkOrderBean = addWorkOrder();
//            String workOrderId = addWorkOrderBean.getBody();
//            System.out.println("新增工单成功 工单ID："+workOrderId);
//        }else{
//            throw new Exception("登录失败");
//        }
//    }

    @DataProvider(name = "data")
    private Object[][] getLoginAccount() {
        return new Object[][]{
                {"13764764202", "111111"}
        };

    }
    @Test(dataProvider = "data")
    private void login(String userName,String password) throws Exception{
        //登录
        //添加参数
        //password md5加密
        String md5Password = Md5Utils.md5(password);
        String jsonStr = "{\"userName\":"+userName+",\"password\":"+md5Password+",\"byType\":\"2\",\"captchaKey\":\"\",\"captcha\":\"\"}";
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String respString = HttpUtils.post(HttpUtils.LOGIN,jsonObject);
        LoginBean bean =  (LoginBean) JSONObject.toBean(JSONObject.fromObject(respString),LoginBean.class);
        if(200 == bean.getHeads().getCode()){
            //设置token
            HttpUtils.token = bean.getBody().getAccess_token();
            System.out.println("登录成功:"+bean.getBody().getRealName());
        }else{
            throw new Exception("登录失败:"+bean.getHeads().getMessage());
        }
    }

    //直接执行addWorkOrder，依赖login，如果login失败，则直接忽略addWorkOrder
    @Test(dependsOnMethods = {"login"})
    private void addWorkOrder(){
        System.out.println("新增工单开始");
        String jsonStr = "{\"type\":\"DEVICE\",\"requirementType\":\"BUY\",\"productCategoryId\":\"385857981465493506\",\"emergencyLevel\":\"LOW\",\"businessType\":\"NON_ASSET_MANAGEMENT\",\"customerId\":\"1130431085877006336\",\"workOrderCode\":\"\",\"accountingUnitId\":\"1022306236676313088\",\"accountingUnitName\":\"上海凯渡商贸有限公司\",\"saleContractCode\":\"STKAD1801160011\",\"saleContractStartDate\":1491523200000,\"saleContractEndDate\":1586131200000,\"goodsList\":[{\"expectedDelivery\":1708444800000,\"keduSparePartCode\":\"\",\"measurementUnit\":\"套\",\"originalCode\":\"\",\"originalModel\":\"\",\"originalName\":\"\",\"parameter\":\"\",\"quantity\":2,\"remark\":\"\",\"skuId\":\"\",\"goodsImageList\":[{\"url\":\"https://qa.api.zhongtai.keduhealthcare.com/912655416532537344/1708354172664/11.png\",\"attachId\":\"FkhJhI8JC2elbI65ugR_BlZVmlNF\",\"name\":\"11.png\",\"attachName\":\"11.png\",\"uid\":\"1708354172660\",\"size\":\"216798\",\"type\":\"image/png\",\"defaultFlag\":false,\"annexId\":\"FkhJhI8JC2elbI65ugR_BlZVmlNF\",\"index\":\"row_3960\"}],\"deleteIdLists\":[],\"workOrderDeviceId\":\"\",\"workOrderId\":\"\",\"referrerName\":\"\",\"referrerPhone\":\"\",\"referrerCompanyName\":\"\",\"categoryAllName\":\"医用软件>数据处理软件>监护软件\",\"referralUrl\":\"\",\"categoryId\":\"385858100923465728\",\"index\":0,\"name\":\"create by testNG\",\"sparePartImageList\":[{\"url\":\"https://qa.api.zhongtai.keduhealthcare.com/912655416532537344/1708354172664/11.png\",\"attachId\":\"FkhJhI8JC2elbI65ugR_BlZVmlNF\",\"name\":\"11.png\",\"attachName\":\"11.png\",\"uid\":\"1708354172660\",\"size\":\"216798\",\"type\":\"image/png\",\"defaultFlag\":false,\"annexId\":\"FkhJhI8JC2elbI65ugR_BlZVmlNF\",\"index\":\"row_3960\"}],\"annexInfoList\":[{\"url\":\"https://qa.api.zhongtai.keduhealthcare.com/912655416532537344/1708354172664/11.png\",\"attachId\":\"FkhJhI8JC2elbI65ugR_BlZVmlNF\",\"name\":\"11.png\",\"attachName\":\"11.png\",\"uid\":\"1708354172660\",\"size\":\"216798\",\"type\":\"image/png\",\"defaultFlag\":false,\"annexId\":\"FkhJhI8JC2elbI65ugR_BlZVmlNF\",\"index\":\"row_3960\"}],\"delImageIds\":[]}],\"referrerName\":\"\",\"referrerPhone\":\"\",\"referrerCompanyName\":\"\",\"productcategoryAllName\":\"\",\"includeSparePartFlag\":1,\"faultType\":3,\"requirementRemark\":\"\",\"requirementImageList\":[],\"sparePartList\":[],\"customerName\":\"嘉兴市妇幼保健院\",\"accountingDepartmentName\":\"工程师管理团队\",\"serviceDetailList\":[],\"warehouseName\":\"\",\"warehouseId\":\"\",\"saleContractCustomerName\":\"嘉兴市妇幼保健院\",\"inventoryRespDTOList\":[],\"salesmanUserId\":\"912655416532537344\",\"salesmanUserName\":\"曾春苗\",\"salesmanDepartmentId\":\"1097872313136844800\",\"salesmanDepartmentName\":\"供应链管理\",\"applicantId\":\"912655416532537344\",\"applicantName\":\"曾春苗\",\"applicantPhone\":\"13764764202\",\"applicantDepartmentId\":\"1097872313136844800\",\"applicantDepartmentName\":\"供应链管理\",\"productCategoryName\":\"医用软件\",\"finalUserId\":\"1130431085877006336\",\"finalUserName\":\"嘉兴市妇幼保健院\",\"receiver\":{\"postcode\":\"\",\"provinceId\":\"829415619152711680\",\"provinceName\":\"河南省\",\"cityId\":\"829415626706653184\",\"cityName\":\"周口市\",\"areaId\":\"829648131594522624\",\"areaName\":\"项城市\",\"detailAddress\":\"普陀区长征镇金沙江路1340弄172支弄14号内14号楼第一层102室\",\"name\":\"王磊\",\"phone\":\"13917831914\",\"workOrderId\":\"\"},\"draftFlag\":false,\"id\":\"\",\"modelId\":\"\",\"bigCategoryType\":\"DEVICE\",\"inventoryReqDTOList\":[],\"requirementSource\":\"KEDU\",\"turnPurchaseSourceCode\":\"\"}";
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            String respString = HttpUtils.post(HttpUtils.ADD,jsonObject);
            WorkReportBean bean =  (WorkReportBean) JSONObject.toBean(JSONObject.fromObject(respString),WorkReportBean.class);
            String workOrderId = bean.getBody();
            System.out.println("新增工单成功 工单ID："+workOrderId);
        }catch (Exception ignored){

        }

    }

}
