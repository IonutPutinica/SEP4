USE [SEP_Staging]
GO

INSERT INTO [dbo].[Date_staging]
           ([CalendarDate]
           ,[Year]
           ,[MonthName]
           ,[Day])
  select Readings.PlantID,
YEAR(Readings.DateTime),
MONTH(Readings.DateTime),
DAY(Readings.DateTime)
from [SEP].[dbo].[Readings]

