USE [SEP_Staging]
GO

INSERT INTO [dbo].[F_Humidity_Staging]
           (
		   [B_PlantKey],
           [Units_Humidity]
           ,[Date_Value]
           ,[Time_Value])
     Select
	 PlantID,
	 Humidity,
convert(date, [ReadingDate]) as [Date],
 convert(time, convert(time, [ReadingDate])) as [Time]
  from [SEP].[dbo].Readings


GO


