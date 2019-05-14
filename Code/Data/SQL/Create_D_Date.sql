CREATE TABLE [D_Date](dateKey int IDENTITY(1,1),[CalendarDate] DATETIME,
[Year] nvarchar(50),MonthName nvarchar(50), Day nvarchar(50))
 go
 DECLARE @StartDate DATETIME 
 DECLARE @EndDate DATETIME SET @StartDate= '2019-01-01'
SET @EndDate= DATEADD(d, 1095, @StartDate)
WHILE @StartDate<= @EndDate BEGIN INSERT INTO [D_Date]
(CalendarDate,
Year,
MonthName,
Day) SELECT @StartDate,
DATENAME(year,@StartDate),
DATENAME(month, @StartDate),
DATENAME(weekday,@startDate)
 SET @StartDate= DATEADD(dd, 1, @StartDate)
END
