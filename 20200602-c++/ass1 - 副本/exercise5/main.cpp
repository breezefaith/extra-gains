//=======================
//		main.cpp
//=======================

// main function for the RPG style game

#include <iostream>
#include <cstdlib>
#include <string>

#include "swordsman.h"
#include "archer.h"
#include "mage.h"
using namespace std;


int main()
{
	string tempName;
	bool success=0;		//flag for storing whether operation is successful
	cout <<"Please input player's name: ";
	cin >>tempName;		// get player's name from keyboard input
	player *human = NULL;		// use pointer of base class, convenience for polymorphism
	int tempJob;		// temp choice for job selection
	do
	{
		cout <<"Please choose a job: 1 Swordsman, 2 Archer, 3 Mage"<<endl;
		cin>>tempJob;
		system("cls");		// clear the screen
		switch(tempJob)
		{
		case 1:
			human=new swordsman(1,tempName);	// create the character with user inputted name and job
			success=1;		// operation succeed
			break;
		case 2:
			human = new archer(1, tempName);	// create the character with user inputted name and job
			success = 1;		// operation succeed
			break;
		case 3:
			human = new mage(1, tempName);	// create the character with user inputted name and job
			success = 1;		// operation succeed
			break;
		default:
			break;				// In this case, success=0, character creation failed
		}
	}while(success!=1);		// so the loop will ask user to re-create a character

	int tempCom;			// temp command inputted by user
	int nOpp=0;				// the Nth opponent

	srand((unsigned)time(NULL));
	player* enemy_ptr = NULL;
	for(int i=1;nOpp<5;i+=2)	// i is opponent's level
	{
		nOpp++;
		system("cls");
		cout<<"STAGE" <<nOpp<<endl;
		cout<<"Your opponent, a Level "<<i<<" Swordsman."<<endl;
		system("pause");

		int job = rand() % 3 + 1;
		switch (job)
		{
		case 1:
			enemy_ptr = new swordsman(i, "Warrior");
			break;
		case 2:
			enemy_ptr = new archer(i, "Warrior");
			break;
		case 3:
			enemy_ptr = new mage(i, "Warrior");
			break;
		default:
			enemy_ptr = new swordsman(i, "Warrior");
			break;
		}
		//swordsman enemy(i, "Warrior");	// Initialise an opponent, level i, name "Junior"
		human->reFill();				// get HP/MP refill before start fight
		
		while(!human->death() && !(*enemy_ptr).death())	// no died
		{
			success=0;
			while (success!=1)
			{
				showinfo(*human,*enemy_ptr);				// show fighter's information
				cout<<"Please give command: "<<endl;
				cout<<"1 Attack; 2 Special Attack; 3 Use Heal; 4 Use Magic Water; 0 Exit Game"<<endl;
				cin>>tempCom;
				switch(tempCom)
				{
				case 0:
					cout<<"Are you sure to exit? Y/N"<<endl;
					char temp;
					cin>>temp;
					if(temp=='Y'||temp=='y')
						return 0;
					else
						break;
				case 1:
					success=human->attack(*enemy_ptr);
					human->isLevelUp();
					(*enemy_ptr).isDead();
					break;
				case 2:
					success=human->specialatt(*enemy_ptr);
					human->isLevelUp();
					(*enemy_ptr).isDead();
					break;
				case 3:
					success=human->useHeal();
					break;
				case 4:
					success=human->useMW();
					break;
				default:
					break;
				}
			}
			if (!(*enemy_ptr).death())		// If AI still alive

			{
				switch (job)
				{
				case 1:
					((swordsman*)enemy_ptr)->AI(*human);
					break;
				case 2:
					((archer*)enemy_ptr)->AI(*human);
					break;
				case 3:
					((mage*)enemy_ptr)->AI(*human);
					break;
				default:
					((swordsman*)enemy_ptr)->AI(*human);
					break;
				}
			}
			else							// AI died
			{
				cout<<"YOU WIN"<<endl;
				human->transfer(*enemy_ptr);		// player got all AI's items
				delete enemy_ptr;
			}
			if (human->death())
			{
				system("cls");
				cout<<endl<<setw(50)<<"GAME OVER"<<endl;
				//6_???????????		// player is dead, program is getting to its end, what should we do here?
				delete human;
				delete enemy_ptr;
				system("pause");
				return 0;
			}
		}
	}
	//7_?????????			// You win, program is getting to its end, what should we do here?
	delete human;
	delete enemy_ptr;
	system("cls");
	cout<<"Congratulations! You defeated all opponents!!"<<endl;
	system("pause");
	return 0;
}
		

