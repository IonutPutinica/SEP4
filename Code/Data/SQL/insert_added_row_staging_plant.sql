USE [SEP_Staging]
GO

INSERT INTO [dbo].[Plant_Dimension_Staging]
           ([B_PlantKey]
           ,[PlantName]
           ,[validFrom]
           ,[validTo])
  select PlantID,
  PlantName,
  GETDATE(),
  '2099-01-01'
  from SEP.dbo.Readings
  where PlantID in
  ( 
  (select PlantID
  from SEP.dbo.Readings
  )
  except
  (
  (select PlantKey from SEPDimension.dbo.D_Plant
  )
  )
  )

   

GO


