FROM mcr.microsoft.com/dotnet/aspnet:10.0 AS base
WORKDIR /app
EXPOSE 5000

FROM mcr.microsoft.com/dotnet/sdk:10.0 AS build
ARG BUILD_CONFIGURATION=Release
WORKDIR /src
COPY ["StockExchangeDotNetBE/StockExchangeDotNetBE.csproj", "StockExchangeDotNetBE/"]
COPY ["StockExchange.BusinessLogic/StockExchange.BusinessLogic.csproj", "StockExchange.BusinessLogic/"]
COPY ["StockExchange.DataAccess/StockExchange.DataAccess.csproj", "StockExchange.DataAccess/"]
RUN dotnet restore "StockExchangeDotNetBE/StockExchangeDotNetBE.csproj"
COPY . .
WORKDIR "/src/StockExchangeDotNetBE"
RUN dotnet build "./StockExchangeDotNetBE.csproj" -c $BUILD_CONFIGURATION -o /app/build

FROM build AS publish
ARG BUILD_CONFIGURATION=Release
RUN dotnet publish "./StockExchangeDotNetBE.csproj" -c $BUILD_CONFIGURATION -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .

ENV ASPNETCORE_URLS=http://0.0.0.0:5000
ENV ASPNETCORE_ENVIRONMENT=Production

ENTRYPOINT ["dotnet", "StockExchangeDotNetBE.dll"]
