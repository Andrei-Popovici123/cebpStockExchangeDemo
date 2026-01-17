using StockExchange.BusinessLogic.Interfaces;
using StockExchange.DataAccess.DTOs;
using StockExchange.DataAccess.Models;
using StockExchange.DataAccess.Repositories.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.BusinessLogic
{
    public class UserService : IUserService
    {
        private readonly IRepository<User> _userRepository;
        public UserService(IRepository<User> userRepository)
        {
            _userRepository = userRepository;
        }

        public async Task<IEnumerable<UserDto>> GetAllUsersAsync()
        {
            var users = await _userRepository.GetAllAsync();

            return users.Select(user => new UserDto
            {
                Id = user.Id,
                UserName = user.UserName,
                Role = user.Role,
                CreatedAt = user.CreatedAt
            });
        }

        public async Task<UserDto> GetUserByIdAsync(int id)
        {
            var user = await _userRepository.GetByIdAsync(id);
            if(user == null)
            {
                throw new Exception("User not found");
            }

            return new UserDto
            {
                Id = user.Id,
                UserName = user.UserName,
                Role = user.Role,
                CreatedAt = user.CreatedAt
            };
        }

        public async Task<UserDto> UpdateUserAsync(int id, UserUpdateDto user)
        {
            var userToUpdate = await _userRepository.GetByIdAsync(id);
            if (user == null)
            {
                throw new Exception("User not found");
            }
            userToUpdate.UserName = user.UserName;

            var newUser = await _userRepository.UpdateAsync(userToUpdate);

            return new UserDto
            {
                Id = newUser.Id,
                UserName = newUser.UserName,
                Role = newUser.Role,
                CreatedAt = newUser.CreatedAt
            };
        }
    }
}
