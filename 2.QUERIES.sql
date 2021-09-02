
with apollo18 as 
	        					(select cars.model,COUNT(*) as counter
	        					from episkeues
	        					RIGHT JOIN cars ON episkeues.frame_number = cars.frame_number
	        					GROUP BY cars.model)
	        					SELECT *
	        					from apollo18
	        					where counter=(Select max(counter) from apollo18);


select date_part('month', start_date) as month ,date_part('year', start_date) as year , avg(estimated_cost) as average from episkeues group by month,year order by year,month asc;

WITH pwlhsh as (
	        					SELECT  sum(value),kwd_pwlhth 
	        					from pwlhseis  where eidos_agoras='Pwlhsh se idiwth' 
	        					group by kwd_pwlhth 
	        					) 
	        					, agora as   
	        					(
	        					SELECT sum(value),kwd_pwlhth
	        					from pwlhseis  where eidos_agoras='Agora se idiwth'  
	        					group by kwd_pwlhth 
	        					) 
	        					,teliko as
	        					(
	        					SELECT coalesce(pwlhsh.kwd_pwlhth,agora.kwd_pwlhth) as pwlhths, coalesce(pwlhsh.sum, 0) as sum1,coalesce(agora.sum, 0) as sum2
	        					from pwlhsh
	        					FULL OUTER JOIN agora ON pwlhsh.kwd_pwlhth=agora.kwd_pwlhth
	        					)
	        					SELECT pwlhths,sum1-sum2 as maxx
	        					from teliko 
	        					where sum1-sum2=(select max(sum1-sum2) from teliko);

SELECT kwd_episkeuhs,stop_date
	        					FROM episkeues 
	        					WHERE stop_date IS NULL;

select date_part('month', start_date) as month,date_part('year', start_date) as year, kwd_episkeuhs,kwd_texnikou  
	        					from episkeues  
	        					where date_part('month', start_date)=date_part('month', NOW()) and date_part('year', start_date)=date_part('year', NOW()) and kwd_texnikou='"+kwdikos_texnikou+"'
	        					group by month,year,kwd_episkeuhs
	        					order by year,month asc;

with spaceship as (select frame_number,date_part('year', stop_date) as year,count(*) as c  
	        					FROM episkeues where date_part('year', stop_date)=date_part('year', NOW())  
	        					GROUP BY year,frame_number 
	        					ORDER BY C desc)
	        					SELECT * FROM spaceship 
	        					where c>1;
