USE [SEPDimension]
GO

INSERT INTO [dbo].[D_Plant]
           ([PlantKey]
           ,[B_PlantKey]
           ,[PlantName])
     Select 
	 PlantKey,
	 B_PlantKey,
	 PlantName

	 from [SEP_Staging].[dbo].Plant_Dimension_Staging
GO


