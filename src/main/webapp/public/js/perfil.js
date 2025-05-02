const elementImg = document.getElementById('avatar');
var randomNumber = Math.floor(Math.random() * 1000);

let ImageSrc = `https://api.dicebear.com/6.x/thumbs/svg?seed=${randomNumber}&scale=60&radius=20&backgroundColor=0a5b83,f1f4dc&backgroundType[]&randomizeIds=true&eyes=variant6W16,variant1W10,variant2W10,variant1W16,variant2W16,variant3W10&face=variant5&mouth=variant2,variant4,variant5&shapeColor=0a5b83,1c799f,f1f4dc`;

elementImg.setAttribute("src", ImageSrc);

