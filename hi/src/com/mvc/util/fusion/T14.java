package com.mvc.util.fusion;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;

/**
 * ��дһ��ְ���������ĳ��λ������ְ���Ļ���Ϣ��ְ���ţ������Ա𣬹��ʣ����й��?�������չ������򣬸���������ְ����ɾ��ְ����Ϣ��
 * @author Administrator
 *Ҫ�Լ����еĶ����ĳ���Խ������������ַ�ʽ��

a. һ����Ҫ���������ʵ��comparable�ӿڵ�compareTo������Ȼ��Ѷ������list��Ȼ�����Collections.sort(list);
b. һ���ǲ���Ҫ������������κθĶ�������Comparator�ӿڵ�ʵ����C��Ȼ�� �Ѷ������list��Ȼ�����Collections.sort(list, C);
 */
class Staff implements Comparable<Staff>{
	@Override
    public String toString() {
        return "id="+id+",name="+name+",sex="+sex+",salary="+salary;
    }
	
	List<Staff> sta=new ArrayList<Staff>();
	ListIterator<Staff>listIter=null;
	public Staff(){
		
	}
	public Staff(int id, String name, String sex, double salary) {
	this.id=id;
	this.name=name;
	this.sex=sex;
	this.salary=salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	private int id;
	private String name;
	private String sex;
	private double salary;
	
	public void salarySort() throws IOException{
		int num;
		System.out.print(" 1.>�����������ѯ."+"\n"+
		       " 2.>�����ʽ����ѯ."+"\n"+
		       " 3.>�������˵�."+"\n"+
		       " 4.>�˳�ϵͳ."+"\n"); 
	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
	  System.out.println("��ѡ����Ҫ��������Ŀ(1-4):");
	  num=br.read();
	  switch(num) {
	  case 49: checkAsc(); break;
	  case 50: checkDesc(); break;
	  case 51:  index(); break;
	  case 52:  System.exit(0); break;
	  }
	
	
	}

	public void checkAsc() throws IOException{
		Collections.sort(sta);
		for(Staff s:sta){
            System.out.println(s.getId()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getSalary());
			}
		salarySort();
		}
	
	public void checkDesc() throws IOException{
		Collections.sort(sta);
		listIter=sta.listIterator(sta.size());
		while(listIter.hasPrevious()){
			System.out.println(listIter.previous());
		}
		salarySort();
	}
	public void checkName() throws IOException{
		int num;
		listIter=sta.listIterator();
		String inputName;
		inputName=JOptionPane.showInputDialog("����������");
		boolean a=false;
		for(int i=0; i<sta.size();i++){
			if(sta.get(i).getName().equals(inputName)){
				System.out.println(sta.get(i));
				a=true;
			}
			System.out.println("�Ƿ�Ҫɾ�������Ϣ");
			System.out.print(" 1.>��."+"\n"+
				       " 2.>��."+"\n"); 
			 BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
			  System.out.println("��ѡ����Ҫ��������Ŀ(1-2):");
			  num=br.read();
			  switch(num) {
			  case 49: 
				  sta.remove(i);
			  System.out.println("ɾ��ɹ���,ʣ��ְ��");
			  for(int z=0;z<sta.size();z++){
				  System.out.println(sta.get(z));
			  }
				  break;
			  case 50: index(); break;}//switch
		
		}//for
			if(a==false){
			System.out.println("���޴���");
			
		System.out.println("�Ƿ������ң�");
		System.out.print(" 1.>��."+"\n"+
			       " 2.>��."+"\n"); 
		 BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
		  System.out.println("��ѡ����Ҫ��������Ŀ(1-2):");
		  num=br.read();
		  switch(num) {
		  case 49: checkName(); break;
		  case 50: index(); break;
		  }
			}//if(a==false)
		
	}//checkName
	public void index() throws IOException{
		System.out.println("ְԱ����ϵͳ");
		System.out.println("1.>���չ�������鿴Ա��������Ϣ");
		System.out.println("2.>����������Ա��");
		System.out.println("3.>�˳�");
		System.out.println("��ѡ����Ҫ��������Ŀ(1-3):");
		int num;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
		  
		  num=br.read();
		  switch(num) {
		  case 49: salarySort(); break;
		  case 50: checkName(); break;

		  case 51: System.exit(0);  break;
	}
	
 }
	
	


	 

	@Override
	public int compareTo(Staff s) {
		 if(s!=null){
	            if(this.getSalary()>s.getSalary()){

	               return 1;
	            }else if(this.getSalary()==s.getSalary()){

	               return 0;

	            }

	       }
	        return -1;
	    }
	
//	@Override  
//    public boolean equals(Object obj){  
//        if(obj == null){  
//            return false;  
//        }else {           
//                if(this.getClass() == obj.getClass()){  
//                    Staff u = (Staff) obj;  
//                    if(this.getName().equals(u.getName())){  
//                        return true;  
//                    }else{  
//                        return false;  
//                    }  
//                  
//            }else{  
//                return false;  
//            }  
//        }             
//    }  
//
//	public int hashCode(){
//		return this.name.hashCode()*this.id;
//		
//	}
	
	}//CLASS��ĩβ
	

public class T14 {
public static void main(String args[])throws IOException{
	Staff staf=new Staff();	
	staf.sta.add(new Staff(1,"gyb","��",10000));
	staf.sta.add(new Staff(2,"zxy","Ů",1000));
	staf.sta.add(new Staff(3,"gbs","��",9999));
	staf.index();
}
}
	


