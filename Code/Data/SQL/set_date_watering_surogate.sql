USE [SEP_Staging]
GO

UPDATE [dbo].[F_Watering_Staging]
   SET [DateKey] = (Select SEPDimension.dbo.D_Date.dateKey from SEPDimension.dbo.D_Date
   where convert(date,[CalendarDate]) = Date_Value) 
GO


