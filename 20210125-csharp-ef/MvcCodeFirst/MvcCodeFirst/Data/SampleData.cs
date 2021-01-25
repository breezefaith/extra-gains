using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using MvcCodeFirst.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MvcCodeFirst.Data
{
    public class SampleData
    {
        public static void Initialize(IApplicationBuilder app)
        {
            using (var serviceScope = app.ApplicationServices.GetService<IServiceScopeFactory>().CreateScope())
            {
                var context = serviceScope.ServiceProvider.GetService<ApplicationDbContext>();
                context.Database.EnsureCreated();

                // Look for any teams.
                if (context.Teams.Any())
                {
                    return;   // DB has already been seeded
                }

                var teams = GetTeams().ToArray();
                context.Teams.AddRange(teams);
                context.SaveChanges();

                var players = GetPlayers(context).ToArray();
                context.Players.AddRange(players);
                context.SaveChanges();
            }
        }

        public static List<Team> GetTeams()
        {
            List<Team> teams = new List<Team>() {
            new Team() {
                TeamName="Lakers",
                City="Los Angeles",
                Province="California",
                Country="USA",
            },
            new Team() {
                TeamName="Golden State Warriors",
                City="Oakland",
                Province="California",
                Country="USA",
            },
            new Team() {
                TeamName="Rockets",
                City="Houston",
                Province="Texas",
                Country="USA",
            },
            new Team() {
                TeamName="Thunder",
                City="Oklahoma City",
                Province="Oklahoma",
                Country="USA",
            },
            new Team() {
                TeamName="Pelicans",
                City="New Orleans",
                Province="Louisiana",
                Country="USA",
            },
            new Team() {
                TeamName="Raptors",
                City="Toronto",
                Province="Ontario",
                Country="Canada",
            },
            new Team() {
                TeamName="Celtics",
                City="Boston",
                Province="Massachusetts",
                Country="USA",
            },
        };

            return teams;
        }

        public static List<Player> GetPlayers(ApplicationDbContext context)
        {
            List<Player> players = new List<Player>() {
            new Player {
                FirstName = "LeBron",
                LastName = "James",
                TeamName = context.Teams.Find("Lakers").TeamName,
                Position = "Shooting Guard"
            },
            new Player {
                FirstName = "Kevin",
                LastName = "Durant",
                TeamName = context.Teams.Find("Golden State Warriors").TeamName,
                Position = "Power Forward"
            },
            new Player {
                FirstName = "Stephen",
                LastName = "Curry",
                TeamName = context.Teams.Find("Golden State Warriors").TeamName,
                Position = "Point Guard"
            },
            new Player {
                FirstName = "James",
                LastName = "Harden",
                TeamName = context.Teams.Find("Rockets").TeamName,
                Position = "Shooting Guard"
            },
        };

            return players;
        }
    }

}
