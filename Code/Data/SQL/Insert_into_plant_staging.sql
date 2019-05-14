USE [SEP_Staging]
GO

INSERT INTO [dbo].[Plant_Dimension_Staging]
           ([B_PlantKey]
           ,[PlantName]
		   ,validFrom
		   ,validTo)

           Select 
		   PlantID,
		   PlantName,
		   '2019-01-01'
		   ,'2099-01-01'
		   from [SEP].[dbo].[Readings]

GO


