# antifire checker
a warning plugin for antifire potions: get yelled at when your antifire is falling off so you don't get sm0ked

more readme yet to be done

# major features
- [x] track the remaining ticks left on your antifire potions
- [x] displays remaining ticks left in an infobox, and changes the text to red in the "danger zone" (configurable, default: 25 ticks = 15 seconds left)
- [ ] optional big blinky overlay when you're in the danger zone 
- [ ] optional start spamming you with notifications when you're in the danger zone
- [ ] only enable around dragons
  - this requires finding a list of dragons that you need an antifire around somewhere
  - if we did this, also maybe some kind of special logic based on [what kind of antifire you need](https://oldschool.runescape.wiki/w/Dragonfire#Damage_reduction)
- [ ] alternative to above: only enable in certain locations?
  - note to above: we'd have to pre-configure a list if we ever want this to get through plugin hub: ["ID based plugins: Plugins that use player provided IDs for the entirety of their functionality"](https://github.com/runelite/runelite/wiki/Rejected-or-Rolled-Back-Features#not-currently-being-considered)

# prior art
thank you to https://github.com/NCG-RS/Goading-Potion-Helper from whom i ~~stole~~ heavily referenced the initial main structure of the plugin. thanks, mate, you're a lifesaver.
thank you to https://github.com/PortAGuy/thrall-helper who instilled a deep desire to have my RuneLite client flash violently at me when i'm making stupid mistakes. thanks mate, you've actually given me a reflex to use my thralls because i don't want to see that damn reminder box again.
thank you to https://github.com/iant89/UltimateNMZ for inspiring me to new depths of being deeply AFK while playing one of the greatest games ever made: Old School RuneScape

# license
this code is permissively licensed under the (some license or another here, idk which one, whichever is permissible)