USE [SEP_Staging]
GO

INSERT INTO [dbo].[F_CO2_Staging]
           (
		   [B_PlantKey],
           [Units_CO2]
           ,[Date_Value]
           ,[Time_Value])
     Select
	 PlantID,
	 CO2,
convert(date, [ReadingDate]) as [Date],
 convert(time, convert(time, [ReadingDate])) as [Time]
  from [SEP].[dbo].Readings


GO





