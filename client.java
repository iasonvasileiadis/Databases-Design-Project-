import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Efarmogh {
   public static int menu() {
	   Scanner scan=new Scanner(System.in);
	   System.out.println("==============================================================\n"
	   +"Please select one of the following Queries for execution or press -1 for exit\n"
	  +"1:Poia einai ta modela twn autokinhtwn me tis perissoteres zhmies\n "
	  +"2:Poio einai to meso kerdos ths etairias apo episkeues \n"
	  +"3:Poios einai o pwlhths me ton megisto tziro \n"
	  +"4:Poies einai oi episkeues pou vriskode se ekremothta \n"
	  +"5:Poies einai oi ergasies  tou texnikou x ton teleutaio mhna \n"
	  +"6:Poia einai ta autokinhta pou exoun erthei gia episkeuh panw apo mia fora ton teleutaio xrono\n"
	  +"-1:Eksodos\n");
	   return scan.nextInt();
	   
   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number,value;
		String sql="",str,message="",kwdikos_texnikou;
		Scanner input2=new Scanner(System.in);
		 Connection c = null;
	        PreparedStatement prepared_stmt= null;
	        ResultSet rs = null;
	        try {
				
				Class.forName("org.postgresql.Driver");
				
			} catch (ClassNotFoundException e) {
				
				System.out.println("Where is your PostgreSQL JDBC Driver? "
						+ "Include in your library path!");
				e.printStackTrace();
				return;
				
			}
	        
	        number=menu();
	        while(number!=-1) {
	        	
	        	try {
	        		
	        		//Class.forName("org.postgresql.Driver");
	        		c = DriverManager
	        				.getConnection("jdbc:postgresql://localhost:5432/AsprhMera",
	        						"postgres", "1234");
	        		
	        		System.out.println("Opened database successfully");
	        		//String data="Ramesh";
	        		
	        		if(number==1) {
	        			sql="with apollo18 as\r\n" + 
	        					"(select cars.model,COUNT(*) as counter\r\n" + 
	        					"from episkeues\r\n" + 
	        					"RIGHT JOIN cars ON episkeues.frame_number = cars.frame_number\r\n" + 
	        					"GROUP BY cars.model)\r\n" + 
	        					"SELECT *\r\n" + 
	        					"from apollo18\r\n" + 
	        					"where counter=(Select max(counter) from apollo18);";
	        			message="Modela twn autokinhtwn me tis perissoteres zhmies:\nModel:              Zhmies:";
	        			
	        		}else if(number==2) {
	        			sql="select date_part('month', start_date) as month2,date_part('year', start_date) as year2, avg(estimated_cost) from episkeues group by month2,year2 order by year2,month2 asc;";
	        			message="meso kerdos ths etairias apo episkeues ana mhna ";
	        		}else if(number==3) {
	        			sql="WITH pwlhsh as (\n" + 
	        					"SELECT  sum(value),kwd_pwlhth \n" + 
	        					"from pwlhseis  where eidos_agoras='Pwlhsh se idiwth' \n" + 
	        					"group by kwd_pwlhth\r\n" + 
	        					")\n" + 
	        					", agora as \n" + 
	        					"(\r\n" + 
	        					"SELECT sum(value),kwd_pwlhth\n" + 
	        					"from pwlhseis  where eidos_agoras='Agora se idiwth' \n" + 
	        					"group by kwd_pwlhth \n" + 
	        					")\n" + 
	        					",teliko as\r\n" + 
	        					"(\r\n" + 
	        					"SELECT coalesce(pwlhsh.kwd_pwlhth,agora.kwd_pwlhth) as pwlhths, coalesce(pwlhsh.sum, 0) as sum1,coalesce(agora.sum, 0) as sum2\n" + 
	        					"from pwlhsh\n" + 
	        					"FULL OUTER JOIN agora ON pwlhsh.kwd_pwlhth=agora.kwd_pwlhth\n" + 
	        					")\n" + 
	        					"SELECT pwlhths,sum1-sum2 as maxx\n" + 
	        					"from teliko\n" + 
	        					"where sum1-sum2=(select max(sum1-sum2) from teliko);";
	        			message="Pwlhths me ton megisto tziro:\nPwlhths:                    Poso:";
	        		}else if(number==4) {
	        			sql="SELECT kwd_episkeuhs,stop_date\n" + 
	        					"FROM episkeues\n" + 
	        					"WHERE stop_date IS NULL;";
	        			message="Episkeues pou vriskode se ekremothta:\nKwdikos episkeuhs: ";
	        		}else if(number==5) {
	        			System.out.println("Dwse kwdiko texnikou gia anazhthsh:");
	        			kwdikos_texnikou=input2.nextLine();
	        			sql="select date_part('month', start_date) as month,date_part('year', start_date) as year, kwd_episkeuhs,kwd_texnikou \n" + 
	        					"from episkeues \n" + 
	        					"where date_part('month', start_date)=date_part('month', NOW()) and date_part('year', start_date)=date_part('year', NOW()) and kwd_texnikou='"+kwdikos_texnikou+"'\n" + 
	        					"group by month,year,kwd_episkeuhs\n" + 
	        					"order by year,month asc;";
	        			message="Ergasies  tou texnikou "+kwdikos_texnikou+" ton teleutaio mhna:\nKwdikos episkeuhs:";
	        		}else if(number==6){
	        			sql="with spaceship as (select frame_number,date_part('year', stop_date) as year,count(*) as c \n" + 
	        					"FROM episkeues where date_part('year', stop_date)=date_part('year', NOW()) \n" + 
	        					"GROUP BY year,frame_number \n" + 
	        					"ORDER BY C desc)\n" + 
	        					"SELECT * FROM spaceship\n" + 
	        					"where c>1;";
	        			message="Autokinhta pou exoun erthei gia episkeuh panw apo mia fora ton teleutaio xrono:\nFrame_number:              Year:            fores:";
	        		}
	        		
	        		
	        		
	        		
	        		
	        		System.out.println(message);
	        		prepared_stmt = c.prepareStatement(sql);
	        		
	        		
	        		
	        		rs = prepared_stmt.executeQuery();
	        		while ( rs.next() ) {
	        			if(number==6) {
	        				value = rs.getInt("year");
	        				str=rs.getString("frame_number");
	        				
	        				System.out.println(str+"          "+value);
	        			}else if(number==1) {
	        				value=rs.getInt("counter");
	        				str=rs.getString("model");
	        				System.out.println(str+"                   "+value);
	        			}else if(number==2) {
	        				System.out.println(rs.getDouble(1)+rs.getDouble(2)+rs.getDouble("avg"));
	        			}else if(number==3) {
	        				System.out.println(rs.getString("Pwlhths")+"                    "+rs.getDouble("maxx"));
	        			}else if(number==4) {
	        				System.out.println(rs.getString("kwd_episkeuhs"));
	        			}else if(number==5) {
	        				System.out.println(rs.getString(3));
	        			}
	        			
	        			
	        			
	        			
	        			
	        			
	        		}
	        		
	        		
	        		
	        	} catch (SQLException ex) {
	        		Logger.getLogger(Efarmogh.class.getName()).log(Level.SEVERE, null, ex);
	        	} finally {
	        		try {
	        			if (rs != null) {
	        				rs.close();
	        			}
	        			if (prepared_stmt != null) {
	        				prepared_stmt.close();
	        			}
	        			if (c != null) {
	        				c.close();
	        			}
	        		} catch (SQLException ex) {
	        			Logger.getLogger(Efarmogh.class.getName()).log(Level.SEVERE, null, ex);
	        		}
	        	}
	        	number=menu();
	        }
			
		}
	}


