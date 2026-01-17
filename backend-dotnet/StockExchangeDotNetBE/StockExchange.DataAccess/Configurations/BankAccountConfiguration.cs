using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using StockExchange.DataAccess.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Configurations
{
    public class BankAccountConfiguration : IEntityTypeConfiguration<BankAccount>
    {
        public void Configure(EntityTypeBuilder<BankAccount> builder)
        {
            builder.HasKey(ba => ba.Id);

            builder.Property(ba => ba.currency)
                   .IsRequired()
                   .HasMaxLength(3);

            builder.Property(ba => ba.balance)
                   .IsRequired()
                   .HasColumnType("decimal(18,2)");

            builder.HasOne(ba => ba.User)
                   .WithMany(u => u.BankAccounts)
                   .HasForeignKey(ba => ba.userId)
                   .OnDelete(DeleteBehavior.Cascade);
        }
    }
}
