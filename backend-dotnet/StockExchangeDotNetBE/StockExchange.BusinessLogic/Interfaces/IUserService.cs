using StockExchange.DataAccess.DTOs;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.BusinessLogic.Interfaces
{
    public interface IUserService
    {
        Task<IEnumerable<UserDto>> GetAllUsersAsync();
        Task<UserDto> GetUserByIdAsync(int id);
        Task<UserDto> UpdateUserAsync(int id, UserUpdateDto user);
        Task<UserDto> GetUserByUsernameAsync(string username);
    }
}
