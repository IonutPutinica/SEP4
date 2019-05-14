USE [SEPDimension]
GO

INSERT INTO [dbo].[D_TimeW]
           ([hoursSinceWatering])
    Select 
	hoursSinceWatering
	from [SEP].[dbo].Readings
GO


