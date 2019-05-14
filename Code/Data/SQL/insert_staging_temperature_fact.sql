USE [SEP_Staging]
GO

INSERT INTO [dbo].[F_Temperature_Staging]
           (
		   [B_PlantKey],
           [Units_Temperature]
           ,[Date_Value]
           ,[Time_Value])
     Select
	 PlantID,
	 Temperature,
convert(date, [ReadingDate]) as [Date],
 convert(time, convert(time, [ReadingDate])) as [Time]
  from [SEP].[dbo].Readings


GO


