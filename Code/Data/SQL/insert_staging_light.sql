USE [SEP_Staging]
GO

INSERT INTO [dbo].[F_Light_Staging]
           (
		   [B_PlantKey],
           [Units_Light]
           ,[Date_Value]
           ,[Time_Value])
     Select
	 PlantID,
	 Light,
convert(date, [ReadingDate]) as [Date],
 convert(time, convert(time, [ReadingDate])) as [Time]
  from [SEP].[dbo].Readings


GO


