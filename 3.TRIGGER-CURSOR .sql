====================================================CURSOR==================================
CREATE OR REPLACE  FUNCTION provolh2 ()
RETURNS TABLE (kwdikos varchar, ddate date ) AS $$
DECLARE
rec_provolhs RECORD ; 
cur_provolhs CURSOR FOR
	SELECT episkeues.kwd_episkeuhs as kwdikos , episkeues.stop_date as ddate
	FROM episkeues
	where episkeues.stop_date is  null  ;
BEGIN
OPEN cur_provolhs ;
	LOOP
	FETCH cur_provolhs  INTO rec_provolhs;
	EXIT WHEN  not FOUND ;
	kwdikos  := rec_provolhs.kwdikos;
	ddate := rec_provolhs.ddate;
	RETURN next ;
	END LOOP ;
	CLOSE cur_provolhs;
	END  ; $$
	LANGUAGE plpgsql ;
SELECT * FROM provolh2( ) ;
==========================================================TRIGGER=============================
CREATE OR REPLACE FUNCTION  episkeuesfunc() 
RETURNS TRIGGER AS $$
BEGIN 
INSERT INTO cars(frame_number,  model) VALUES (new .frame_number, '--' );
RETURN NEW ;
END ;
$$ 
LANGUAGE plpgsql ;
