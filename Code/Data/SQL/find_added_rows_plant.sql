USE [SEP]
GO

SELECT [PlantID]
      ,[PlantName]
      ,[Temperature]
      ,[Light]
      ,[CO2]
      ,[Humidity]
      ,[AmountOfWater]
      ,[HoursSinceWatering]
      ,[ReadingDate]
  FROM [dbo].[Readings]
  
  where PlantID in
  (
  (Select PlantID from
  Readings
  )
  Except
  (select PlantKey from SEPDimension.dbo.D_Plant)
  )

GO


