package frame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DataDAO{
	Connection con;
	String driver,url,id,pw;
	LoginPage login;
	
	public DataDAO() throws Exception {
		this.driver="oracle.jdbc.driver.OracleDriver";
		this.url="jdbc:oracle:thin:@localhost:1521:xe";
		this.id="hr";
		this.pw="hr";
		Class.forName(driver);
	}
	
	public DataDAO(LoginPage login) throws Exception {
		this.login=login;
		this.driver="oracle.jdbc.driver.OracleDriver";
		this.url="jdbc:oracle:thin:@localhost:1521:xe";
		this.id="hr";
		this.pw="hr";
		Class.forName(driver);
	}
	
	
	
	public int dataInput(String id, String nickname, String password, String name, String gender, String tell, String email) throws Exception{
		this.con=DriverManager.getConnection(this.url,this.id,this.pw);
		Statement stmt = this.con.createStatement();
		String sql="insert into jangiuser(id,nickname,password,name,gender,tell,email) values('"+id+"',"+"'"+nickname+"','"+password+"','"+name+"','"+gender+"','"+tell+"','"+email+"')";
		
		int res=-1;
		res = stmt.executeUpdate(sql);
		
		
		this.con.commit();
		
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		return res;
	}
	
	public boolean nameSerch(String [] arr,int index) throws Exception{//id찾기시 이용.
		boolean r=false;
		this.con=DriverManager.getConnection(this.url,this.id,this.pw);
		Statement stmt = con.createStatement();
		
		String  in="";
		
		if(index==0){
			in="name";
		}
		else if(index==1){
			in="id";
		}
		
		String sql = " select * from jangiuser where "+in+"= '"+arr[0]+"'"; 
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			if(arr[1].equals(rs.getString("email"))){ //검색한 이름과 가지고있는 메일값이 같다면 본인. 
			this.login.id=rs.getString("id");
			this.login.password=rs.getString("password");
			r=true;
			}
		}
		

		this.con.commit();
		
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		return r;
		}
	
	

	public boolean idSerch(String [] arr) throws Exception{ //로그인시 이용.
		boolean r=false;
		this.con=DriverManager.getConnection(this.url,this.id,this.pw);
		Statement stmt = con.createStatement();
	
		String sql = " select * from jangiuser where id = '"+arr[0]+"'"; 
		
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			if (arr[1].equals(rs.getString("password"))) { //비밀번호와 아이디가 동일하다면. 
				r = true;
				this.login.id = rs.getString("id");
				this.login.name = rs.getString("name");
				this.login.nickname = rs.getString("nickname");
				this.login.gen = rs.getString("gender");
				this.login.jum = rs.getInt("jum");
				this.login.gpsu = rs.getString("gpsu");
			}
		}
		
		
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		
		return r;
	}
	
	public boolean deleteidSerch(String [] arr) throws Exception{ //로그인시 이용.
		boolean r=false;
		this.con=DriverManager.getConnection(this.url,this.id,this.pw);
		Statement stmt = con.createStatement();
	
		String sql = " select * from jangiuser where id = '"+arr[0]+"'"; 
		
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			if (arr[1].equals(rs.getString("password"))) { //비밀번호와 아이디가 동일하다면. 
				r = true;
			}
		}
		
		
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		
		return r;
	}
	
	
	public List getUserinfo(int index) throws Exception{
		this.con=DriverManager.getConnection(this.url,this.id, this.pw);
		Statement stmt = con.createStatement();
		String res="";
		
		
		if(index==0){
			res="id";
		}
		else if(index==1){
			res="name";
		}
		else if(index==2){
			res="nickname";
		}
		else if(index==3){
			res="email";
		}
		else if(index==4){
			res="tell";
		}
		
		
		String sql="select * from jangiuser";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List list = new ArrayList();
		
		while(rs.next()){
			list.add(rs.getString(res));
		}
		
		
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		
		return list;
	}
	
	public int dataEdit(String id,String set, String newtext) throws Exception{
		int res = -1;
		
		this.con=DriverManager.getConnection(this.url,this.id, this.pw);
		Statement stmt;
		stmt = con.createStatement();
		
		String sql="update jangiuser set "+set+"='"+newtext+"' where id='"+id+"'";
		
		res = stmt.executeUpdate(sql);
		
		con.commit();
		
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		return res;
	}
	
	public int dataEdit2(String id,String set1, String newtext1,String set2, String newtext2) throws Exception{
		int res = -1;
		
		this.con=DriverManager.getConnection(this.url,this.id, this.pw);
		Statement stmt;
		stmt = con.createStatement();
	//	update jangiuser set jum='20',gpsu='16급' where id='imcts1';
		String sql="update jangiuser set "+set1+"='"+newtext1+"' ,"+set2+"='"+newtext2+"' where id='"+id+"'";
		
		res = stmt.executeUpdate(sql);
		
		con.commit();
		
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		return res;
	}
	
	public String getUserJum(String id) throws SQLException{
		String jum="";
		this.con=DriverManager.getConnection(this.url,this.id, this.pw);
		Statement stmt;
		stmt = con.createStatement();
		
		String sql="select jum from jangiuser where id='"+id+"'";
		
		ResultSet rs = stmt.executeQuery(sql);	
		while(rs.next()){
			jum = rs.getString("jum");
		}
		 
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		return jum;
	}
	
	public int delete(String [] arr) throws Exception{
		int res=-1;
		
		this.con=DriverManager.getConnection(this.url,this.id, this.pw);
		Statement stmt = con.createStatement();
		String sql="delete from jangiuser where id = '"+arr[0]+"'";
		
		res = stmt.executeUpdate(sql);
		
		con.commit();
		
		
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
		
		return res;
	}
	
}


