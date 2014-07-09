package cn.redarmy.service.Service;

public interface GoodService {
	// ��¼,1��ʾ�ɹ�,2��ʾ�Ѿ����ڸ��û�,������ʾʧ��
	public abstract int LogIn(String strPersonName,// �û�����
			String strPhoneNum,// �绰����
			String strMac);// �豸mac��ַ

	// ���Լ���״̬����״̬����,ʧ�ܷ���0,�ɹ�����1
	public abstract int InsertStatus(String strPersonName,// �û���
			String strContent,// ״̬����
			double dLongitude,// ����
			double dLatitude);// γ��

	// ����״̬�鿴��¼����,ʧ�ܷ���0,�ɹ�����1
	public abstract int InsertSearchRecord(String strPersonName,// �û���
			String strContent,// ״̬����
			double dLongitude,// ����
			double dLatitude);// γ��

	// �鿴���Լ�״̬��ͬ������
	public abstract long GetSameStatusPersonNum(String strPersonName, // �û�����
			String strContent,// ����
			long lSeconds,// ָ��ʱ���ڵ���ͬ״̬(��)
			double dLongitude, double dLatitude, int nType);// nTypeΪ1��ʾֻ�鲻��������2��ʾ��ͬʱ��������

	// ��ȡ���ڲ鿴ĳ��״̬������
	public abstract long GetIsSearchingPersonNum(String strPersonName,
			String strContent,// ����
			long lSeconds,// ָ��ʱ���ڵ���ͬ״̬(��)
			double dLongitude, double dLatitude, int nType);// nTypeΪ1��ʾֻ�鲻��������2��ʾ��ͬʱ��������

	// ��ȡϵͳʱ��
	public abstract String GetServiceTime();

	// ����mac��ȡ�˺�
	public abstract String GetAccountName(String strMac);

	// ��ȡע������
	public abstract int GetUsingPersonNum();
}
