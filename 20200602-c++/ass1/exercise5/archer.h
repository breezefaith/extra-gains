#pragma once
#include <iostream>
#include "player.h"

class archer: public player
{
public:
	archer(int lv_in = 1, std::string name_in = "Not Given");
	void isLevelUp();
	bool attack(player &p);
	bool specialatt(player &p);
	void AI(player &p);
};

