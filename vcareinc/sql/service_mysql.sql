CREATE PROCEDURE RUNSQLSCRIPT(SQLSCRIPT VARCHAR(1000))
BEGIN
	PREPARE STMT FROM SQLSCRIPT;
	EXECUTE STMT;
	DEALLOCATE PREPARE STMT;
END;