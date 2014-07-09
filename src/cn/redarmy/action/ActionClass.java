package cn.redarmy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.redarmy.service.Service.GoodService;
import cn.redarmy.service.impl.GoodServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class ActionClass extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	HttpServletResponse response;

	// ����ҵ�����bean
	private GoodService goodService = new GoodServiceImpl();

	// ��¼����ֵ
	int loginret;
	// ����״̬��Ϣ����ֵ
	int insertstatusret;
	// �������ڲ�ѯ��Ϣ��¼����ֵ
	int insertsearchrecordret;
	// ��ͬ״̬����
	long samestatuspersonnum;
	// ���ڲ�ѯͬһ״̬����
	long issearchingpersonnum;
	//ϵͳʱ��
	String servicetime;
	//�˺�����
	String straccountname;
	//ʹ������
	int usingnum;

	public int getloginret() {
		return loginret;
	}

	public void setloginret(int nret) {
		this.loginret = nret;
	}

	public int getinsertstatusret() {
		return insertstatusret;
	}

	public void setinsertstatusret(int nret) {
		this.insertstatusret = nret;
	}

	public int getinsertsearchrecordret() {
		return insertsearchrecordret;
	}

	public void setinsertsearchrecordret(int nret) {
		this.insertsearchrecordret = nret;
	}

	public long getsamestatuspersonnum() {
		return samestatuspersonnum;
	}

	public void setsamestatuspersonnum(long nret) {
		this.samestatuspersonnum = nret;
	}

	public long getissearchingpersonnum() {
		return issearchingpersonnum;
	}

	public void setissearchingpersonnum(long nret) {
		this.issearchingpersonnum = nret;
	}
	public String getservicetime() {
		return servicetime;
	}

	public void setservicetime(String strTime) {
		this.servicetime = strTime;
	}
	
	public String getstraccountname() {
		return straccountname;
	}

	public void setstraccountname(String strName) {
		this.straccountname = strName;
	}

	public void setusingnum(int nret) {
		this.usingnum = nret;
	}

	public int getusingnum() {
		return usingnum;
	}
	
	public String LogIn() {
		String strname1 = "";
		String strname2 = "";
		String strname3 = "";
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			strname1 = this.request.getParameter("strPersonName");
			strname2 = this.request.getParameter("strPhoneNum");
			strname3 = this.request.getParameter("strMac");
		} catch (Exception e) {
			e.printStackTrace();
		}
		loginret = goodService.LogIn(strname1, strname2, strname3);
		return SUCCESS;
	}

	public String InsertStatus() {
		String strname1 = "";
		String strname2 = "";
		String strname3 = "";
		String strname4 = "";
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			strname1 = this.request.getParameter("strPersonName");
			strname2 = this.request.getParameter("strContent");
			strname3 = this.request.getParameter("dLongitude");
			strname4 = this.request.getParameter("dLatitude");
		} catch (Exception e) {
			e.printStackTrace();
		}
		double dLongitude = Double.parseDouble(strname3);
		double dLatitude = Double.parseDouble(strname4);
		insertstatusret = goodService.InsertStatus(strname1, strname2,
				dLongitude, dLatitude);
		return SUCCESS;
	}

	public String InsertSearchRecord() {
		String strname1 = "";
		String strname2 = "";
		String strname3 = "";
		String strname4 = "";
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			strname1 = this.request.getParameter("strPersonName");
			strname2 = this.request.getParameter("strContent");
			strname3 = this.request.getParameter("dLongitude");
			strname4 = this.request.getParameter("dLatitude");
		} catch (Exception e) {
			e.printStackTrace();
		}
		double dLongitude = Double.parseDouble(strname3);
		double dLatitude = Double.parseDouble(strname4);
		insertsearchrecordret = goodService.InsertSearchRecord(strname1,
				strname2, dLongitude, dLatitude);
		return SUCCESS;
	}

	public String GetSameStatusPersonNum() {
		String strname1 = "";
		String strname2 = "";
		String strname3 = "";
		String strname4 = "";
		String strname5 = "";
		String strname6 = "";
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			strname1 = this.request.getParameter("strPersonName");
			strname2 = this.request.getParameter("strContent");
			strname3 = this.request.getParameter("lSeconds");
			strname4 = this.request.getParameter("dLongitude");
			strname5 = this.request.getParameter("dLatitude");
			strname6 = this.request.getParameter("nType");
		} catch (Exception e) {
			e.printStackTrace();
		}
		long lSeconds = Long.parseLong(strname3);
		double dLongitude = Double.parseDouble(strname4);
		double dLatitude = Double.parseDouble(strname5);
		int nType = Integer.parseInt(strname6);
		samestatuspersonnum = goodService.GetSameStatusPersonNum(strname1,
				strname2, lSeconds, dLongitude, dLatitude,nType);
		return SUCCESS;
	}

	public String GetIsSearchingPersonNum() {
		String strname1 = "";
		String strname2 = "";
		String strname3 = "";
		String strname4 = "";
		String strname5 = "";
		String strname6 = "";
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			strname1 = this.request.getParameter("strPersonName");
			strname2 = this.request.getParameter("strContent");
			strname3 = this.request.getParameter("lSeconds");
			strname4 = this.request.getParameter("dLongitude");
			strname5 = this.request.getParameter("dLatitude");
			strname6 = this.request.getParameter("nType");
		} catch (Exception e) {
			e.printStackTrace();
		}
		long lSeconds = Long.parseLong(strname3);
		double dLongitude = Double.parseDouble(strname4);
		double dLatitude = Double.parseDouble(strname5);
		int nType = Integer.parseInt(strname6);
		issearchingpersonnum = goodService.GetIsSearchingPersonNum(strname1,
				strname2, lSeconds, dLongitude, dLatitude,nType);
		return SUCCESS;
	}

	public String GetServiceTime() {
		servicetime = goodService.GetServiceTime();
		return SUCCESS;
	}
	
	public String GetAccountName() {
		String strname1 = "";
		try {
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			strname1 = this.request.getParameter("strMac");
		} catch (Exception e) {
			e.printStackTrace();
		}
		straccountname = goodService.GetAccountName(strname1);
		return SUCCESS;
	}
	
	public String GetUsingPersonNum(){
		usingnum = goodService.GetUsingPersonNum();
		return SUCCESS;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

}
