﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ReadingAccess
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class SEPDimensionEntities : DbContext
    {
        public SEPDimensionEntities() : base("name=SEPDimensionEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<D_Date> D_Date { get; set; }
        public virtual DbSet<D_Plant> D_Plant { get; set; }
        public virtual DbSet<D_Time> D_Time { get; set; }
        public virtual DbSet<F_CO2> F_CO2 { get; set; }
        public virtual DbSet<F_Humidity> F_Humidity { get; set; }
        public virtual DbSet<F_Light> F_Light { get; set; }
        public virtual DbSet<F_Temperature> F_Temperature { get; set; }
        public virtual DbSet<F_Watering> F_Watering { get; set; }
    }
}
