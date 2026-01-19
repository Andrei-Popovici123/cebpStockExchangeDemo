FROM mcr.microsoft.com/dotnet/aspnet:10.0-alpine
WORKDIR /app

COPY /publish .

EXPOSE 5000

ENTRYPOINT ["dotnet", "StockExchangeDotNetBE.dll"]
