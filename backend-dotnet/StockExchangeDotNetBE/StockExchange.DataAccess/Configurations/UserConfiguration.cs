using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using StockExchange.DataAccess.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Configurations
{
    public class UserConfiguration : IEntityTypeConfiguration<User>
    {
        public void Configure(EntityTypeBuilder<User> builder)
        {
            builder.Property(u => u.UserName)
                   .IsRequired()
                   .HasMaxLength(256);

            builder.Property(u => u.Role)
                   .IsRequired();

            builder.Property(u => u.CreatedAt)
                   .IsRequired()
                   .HasDefaultValueSql("GETDATE()");
        }
    }
}
