"The Worthy Order of Curmudgeons" by James Gain

When play begins:
	 say "Perhaps if you explore the laboratories of the Worthy Order of Curmudgeons you will discover more about their nefarious plans. Sunrise is set to be at 5:30 so you better be out by then or the Curmudgeons will catch you.";
         now the command prompt is "[time of day] >";
	now time of day is 4:20 AM.
	
At 5:30 AM:
	say "The Curmudgeons enter their facility and find you there. Dr Turk draws his plasma pistol and blasts you into nothingness.";
	end the story saying "You are dead".

An explanatory note is carried by the player. The description of the explanatory note is "This hastily penned note reads 'I'm sure she is somewhere in the laboratories'."

The Shielded Corridor is a room. "Outside the Laboratories of the Worthy Order of Curmudgeons, the walls are shielded with sonic damping, fire proofing, acid-resistant lacquer, structural supports and storm baffling. (The last a recent addition inspired by Dr Crowther's experiments). The imposing entrance to the Prime Laboratory is in the south wall."

South of the Shielded Corridor is the Cogway. The Cogway is a door. The description of the Cogway is "The Affiliates of the Order are jealous of their secrets and this ornate double door constructed entirely from cogs and wheels is one giant locking mechanism." The Cogway is unlocked and lockable. South of the Cogway is the Prime Laboratory. The indefinite article of Cogway is "the".

The Prime Laboratory is a room. "This massive barrel-vaulted chamber is clustered with half a dozen ongoing experiments. Sparks surge between twisted prongs, sulphorous liquids bubble and murmur, and a half-made automaton rocks itself back and forth in the corner. The hair on the back of your neck rises. This could be from the terrible portent of the experiments conducted here over the centuries or it could be from the actinic charge in the air. [paragraph break] Crowther's Lab is to the west and the Insectarium is to the east. An archway leads south to the flight platform and is also useful for venting in an emergency." 

The Rail is here. The description of the Rail is "A rail and pulley system that runs across the ceiling so that Dr Peabody's infernal flying machines can be carried to the Launch Platform."
The Daedulus Flutter-wing is on the Rail. The Flutter-wing is a vehicle. The description of the Flutter-wing is "Peabody's pride and joy but you would have to be suicidal to try and fly in it."
The Slow Glass is here. The Slow Glass is fixed in place. The description of the Slow Glass is "You are shocked to see Dr Crowther on the other side of the glass, but then you realize that it is only light from earlier this afternoon slowly leaking through." The indefinite article of Slow Glass is "the".
Some Arcane Paraphernalia are here. The spider-silk rope, perpetual machine, Tesla gun, rubber bullet and crystal shards are in the Paraphernalia. The description of the rubber bullet is "A sturdy rubber bullet with slight dints. It looks like it could fit in some weapon." The Paraphernalia is an opaque and openable container. The description of the Paraphernalia is "The detritus of half a hundred failed experiments. There might be something salvagable in here if your are prepared to risk rumagging."  
The Storm Globes are here. The description of the Storm Globes is "Traceries of lightning swirl inside these spheres."
The carefully written note is here. The description of the carefully written note is "A note written with care reads 'I know you wanted it. It's innapropriate but I put it in the congelator. Love, C'."

The Crowther Lab is a room. "The walls of this spherical room are tiled in brass and streaked with soot from Dr Crowther's lightning experiments, but now it is dominated by the Crowther Engine." The Crowther Lab is west of the Prime Laboratory.
The Crowther Engine is here. The Crowther  Engine is a container. The description of the Crowther Engine is "This engine on its own can do the work of a hundred calculation drudges in a fraction of the time, but it is far from small and portable." The indefinite article of the engine is "the". The Crowther Engine is locked.
The Brass Man is here and a device. The description of the Brass Man is "A steam-driven automaton in the shape of a man that is connected by cables to the Crowther Engine. There is a faint whimpering coming from within that sounds almost human but is probably just bottled-up steam."
The doors panel is here. The doors panel is a supporter. On the doors panel is the heavy key. The description of the doors panel is "A panel showing the locked doors and their keys just underneath them. The panel is too high for me to access by hand. You could try hitting the keys with something to make it fall." The description of the heavy key is "A golden key that is surely used to open a door here."


The Clockwork Insectarium is a room. "This room is filled with twisted metal and stained glass constructs in a caricature of a jungle environment. Oversized clockwork beetles, worms, butterflies and spiders move slugishly among the syntetic foliage. A stained glass doorway leads west back to the main laboratory." West of the Insectarium is the Prime Laboratory.
The clockwork insects are here. The description of the clockwork insects is "Each insect is an order of magnitude larger than its biological equivalent, probably because Horner is still having trouble with miniaturisation. They seem to be running low on energy." The indefinite article of insects is "the".

The Morgue is a room. " This room has a noticeably lower temperature. The walls have been fitted with drawers presumably contaning corpses waiting to be experimented on by Dr Crowther. A large metal table containing various tools is positioned in the middle of the room.  A large metallic cabinet is at the back of the room, a label on it says 'Congelator'." The Morgue is north of the Crowther Lab.
The Congelator is here. The congelator is a closed container .The congelator is openable. The description of the Congelator is "Dr Crowther's first great invention. Using the pressure of steam power, Dr Crowther had managed to drop the temperature inside this cabinet to 5 degrees celcius." Inside the Congelator is an appetizing chocolate cupcake. The appetizing chocolate cupcake is an edible. The description of the appetizing chocolate cupcake is "Its velvety chocolate frosting makes your mouth water." After eating appetizing chocolate cupcake, say "'gulp' Nice."

Dr Crowther's study is a room. "This spacy room contains a sofa on the right side and a large bookshelf at the back. In front of you is Dr Crowther's desk."Dr Crowther's study is north of the clockwork insectarium. Dr Crowther's desk is fixed in place and here. The description of Dr Crowther's desk is "A large desk filled withdrawings and scrappy notes. Crowther's life's work right here. You also find some lotion and a roll of tissue paper. Something tells you to not touch them. The desk contains a set of drawers. They could contain something interesting." Dr Crowther's drawers is a closed container. Dr Crowther's drawers are openable. Dr crowther's drawers are part of Dr Crowther's desk. Inside the drawers is an old pump air pistol. The old pump air pistol is a container. The description of the old pump air pistol is "This weapon was make by Crowther when he was a kid. Maybe it still works."

Shooting it with is an action applying to two things. 

Check shooting something with something: 
	if the player is not carrying old pump air pistol, say "You are pathetically unarmed!" instead; 
	if the second noun is old pump air pistol, say "[The second noun] does not fire." instead; 
	if the noun is the old pump air pistol and old pump air pistol contains rubber bullet and the player is  not in the Crowther Lab:
		say "Cannot see why you should.";
	if the noun is the old pump air pistol and old pump air pistol contains rubber bullet and the player is in the Crowther Lab:
		say "'Tak!' The key falls and you grab it";
		move heavy key to player.

	
Understand "shoot [gun] at [something ungunlike]" as shooting it with (with nouns reversed). 

Definition: a thing is ungunlike if it is not the old pump air pistol. 

Understand "shoot [something ungunlike] with [gun]" as shooting it with. Understand "shoot [something] with [something]" as shooting it with. 

Understand "shoot [something] at [something]" as shooting it with (with nouns reversed). Understand "fire [gun] at [something ungunlike]" as shooting it with (with nouns reversed). Understand "fire at [something ungunlike] with [gun]" as shooting it with. Understand "fire at [something] with [something]" as shooting it with. 



South of the Crowther Lab is the metal door. The metal door is a door. The description of the metal door is "An imposing door that piques your interest. The door has a strangely shaped lock." The metal door is locked and lockable. The heavy key unlocks the metal door.

The Curmudgeons tactical room is a room. "On entering you see a large table surrounded by chairs. A map lays flat on it. To your right is a weapons table." The weapons table is here. The Curmudgeons tactical room is south of the metal door. The weapons table is a container. The bomb is fixed in place and a supporter in the weapons table. The description of the bomb is "A large pipe bomb with a timer attached to it. The bomb looks impressively dangerous." The timer is a device and on the bomb. The timer is switched off. The description of the timer is "A timer that is set to detonate the bomb in fifteen minutes." The command table is here and a supporter. The map is on the command table. The description of the map is "A map depicting the nearby town, Mekley with an 'X' marked on the town. The picture of a bomb is also drawn on the town. Could those fiendish Curmudgeons be planning to bomb Mekley?! You have to stop this."

After switching on the timer:
	say "15 minutes. Gotta get out of here.";
        The bomb explodes in 10 turns from now;

At the time when bomb explodes:
	end the story saying "The bomb explodes, destroying the Curmudgeons tower and their laboratories. Unfortunately you did not escape on time and died in the blast."

	
The Surrounding Forest is a backdrop. It is in the Launch Platform, the Tower Base and the Balloon Mooring. The description of the Surrounding Forest is "A dense forest stretches towards the horizon. Nearby is the burnt out shell of a village. (After Dr Corvine's invention of fire-rain the villagers decided to rebuild several miles away)."

The Launch Platform is a Room. The Launch Platform is south of the Prime Laboratory.The description of the Lauch Platform is "This platform has a flight of stairs that lead upwards to the Balloon Mooring and down  the Tower Base. Besides the stairways, the room looks fairly empty."

The Tower Wall is a door. The Tower Wall is down from the Launch Platform and up from the Tower Base. The Tower Wall is unlocked and lockable.

The Tower Base is a room. The Donkey Cart is here and a vehicle.The description of the Tower Base is "The base looks like a garage with your Donkey cart close to the opened doors that lead to the road.".The description of the donkey cart is "Your Donkey, Tobi looks tired from the journey, you should have brought some carrots. Hopefully he will manage the voyage home."

The town is west of the Tower Base.

The Balloon Mooring is a room. It is up from the Launch Platform. The Hot-Air Balloon is here and a vehicle. The description of the Launch Platform is "Up there you can feel the fresh breeze which contrasts with the damp acrid smell of the laboratories. In front of you is a large hot air balloon that looks ready for take off." The description of the Hot-Air Balloon is "The aircraft looks like it could accomodate about six people. The controls look fairly simple and you are confident that you could use it if need be."


The town is a room. The town is west of the Balloon Mooring.

Instead of going town when the player is not in Hot-Air Balloon: 
	say "Cannnot go there on foot. Maybe if you could fly..."

Instead of going west when the player is in Hot-Air Balloon and metal door is unlocked: 
        end the story saying "The bomb explodes, destroying the Curmudgeons tower and their laboratories while you watch, flying away in the Hot-Air Balloon. You Win."

Instead of going down when the player is in Hot-Air Balloon:
	say "Cannot really go there in a Hot-Air Balloon."


		
	



