# RecipesByPhoto Рецепты по фото

Приложение под андроид, написанное на языке Kotlin.

Фотографирует набор продуктов, распознает их и предлагает на их основе рецепты.

Библиотеки: ~~ogle.cloud,~~icasso, okhttp, moshi...

~~пользуется Goggle Vision API для распознования объектов на картинке~~
Используется предобученная модель сегментации ингредиентов еды.

Поднят свой сервер на FastAPI и torch+cu111 для распознования.

А также API https://spoonacular.com/food-api/docs для получения рецептов




Демонстрация работы: https://youtu.be/iSZxbwD9bg4

Скачать APK: https://drive.google.com/file/d/1tA6JLjZ3v_uNYHq9IYZswaqZx7iAoKhS/view?usp=sharing


@inproceedings{wu2021foodseg,
	title={A Large-Scale Benchmark for Food Image Segmentation},
	author={Wu, Xiongwei and Fu, Xin and Liu, Ying and Lim, Ee-Peng and Hoi, Steven CH and Sun, Qianru},
	booktitle={Proceedings of ACM international conference on Multimedia},
	year={2021}
}
