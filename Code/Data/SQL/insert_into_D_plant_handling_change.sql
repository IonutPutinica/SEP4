USE [SEPDimension]
GO

INSERT INTO [dbo].[D_Plant]
           ([PlantKey]
           ,[B_PlantKey]
           ,[PlantName]
           ,[Valid_From]
           ,[Valid_To])
   Select
   PlantKey
   ,B_PlantKey
   ,PlantName
   ,validFrom
   ,validTo
   from SEP_Staging.dbo.Plant_Dimension_Staging

GO


