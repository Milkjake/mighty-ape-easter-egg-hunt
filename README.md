# Mighty Ape Easter Egg Hunt

## Background

Welcome to my Mighty Ape Easter Egg Hunt program!

The Mighty Ape Easter Egg Hunt is an annual event where you have to find some easter eggs hidden on various product pages.

You are given clues as to the products that have the eggs.

The products found across various departments like Games, Movies, Music etc.

More info on the 2023 Easter Egg Hunt [here](https://www.mightyape.co.nz/blog/7413/easter-egg-hunt-win-a-playstation-5-bundle-and-more).


## Why I chose to write this program

I usually do the Easter egg hunt manually, going through various pages to try and find Easter eggs.

This year (2023) I simply decided to try and find them programmatically as a way to automate a repetitive task and learn something new along the way.

What I learnt from coding this program is scraping HTML data in Java. The [jsoup](https://jsoup.org/) library was very helpful in achieving the goals I had for this program.


## What the program does

The program scrapes the landing pages of each of the given departments for a list of product links.

Each product page is then scraped to check for a specific Easter egg HTML element.

If found, the link to page is collected as well as the colour of the Easter egg.

After scraping all the pages, the complete list is outputted to the console.


## The 2023 Egg Hunt

For the 2023 Egg Hunt, the program was able to find 7/8 eggs. The reason for the last egg not being found is because it was quite deep inside its department's category tree.

The program was designed to only scrape the landing page of each department.

This can be solved by expanding the scraping by searching through each departments' subcategories in addition to the landing page.

I don't think I'll introduce that though as it would increase the scraping time considerably.
