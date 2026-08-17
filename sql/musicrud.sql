CREATE TABLE
    `artist` (
        id int (11) NOT NULL AUTO_INCREMENT,
        `artistic_name` varchar(100) NOT NULL,
        `name` varchar(50) NOT NULL,
        `lastnames` varchar(100) NOT NULL,
        `country_of_origin` varchar(60) NOT NULL,
        PRIMARY KEY (`id`)
    );

INSERT INTO
    `artist`
VALUES
    (
        1,
        'Metallica',
        'James Alan',
        'Hetfield Nicks',
        'USA'
    ),
    (
        2,
        'Queen',
        'Farrokh',
        'Bulsara Dashti',
        'United Kingdom'
    ),
    (
        3,
        'Nirvana',
        'Kurt Donald',
        'Cobain Fradenburg',
        'USA'
    ),
    (
        4,
        'Coldplay',
        'Christopher Anthony',
        'Martin Buckland',
        'United Kingdom'
    ),
    (
        5,
        'Billie Eilish',
        'Billie Eilish',
        'O’Connell Baird',
        'USA'
    ),
    (
        6,
        'Shakira',
        'Shakira Isabel',
        'Mebarak Ripoll',
        'Colombia'
    ),
    (
        7,
        'Daddy Yankee',
        'Ramón Luis',
        'Ayala Rodríguez',
        'Puerto Rico'
    ),
    (8, 'Drake', 'Aubrey Drake', 'Graham Sher', 'USA'),
    (
        9,
        'Adele',
        'Adele Laurie',
        'Adkins Blue',
        'United Kingdom'
    ),
    (
        10,
        'Arctic Monkeys',
        'Alexander David',
        'Turner Thompson',
        'Candada'
    ),
    (
        11,
        'Pedro Infante',
        'Pedro',
        'Infante Cruz',
        'México'
    );


ALTER TABLE song DROP CONSTRAINT song_ibfk_1;
ALTER TABLE song DROP CONSTRAINT song_ibfk_2;
 
CREATE TABLE song (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `genre_id` int(11) NOT NULL,
    `album_id` int(11) NOT NULL,
    `title` varchar(100) NOT NULL,
    `lyrics` text NOT NULL,
    `duration` time NOT NULL,
    `release_date` date NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `song_fk_1` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE , 
    CONSTRAINT `song_fk_2` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE 
);


INSERT INTO `song` VALUES (1,2,1,'Battery','res/albums/lyrics/master-of-puppets/battery.txt','00:05:12','1986-03-03'),(2,2,1,'Master Of Puppets','res/albums/lyrics/master-of-puppets/master-of-puppets.txt','00:08:35','1986-03-03'),(3,2,1,'The Thing That Should Not Be','res/albums/lyrics/master-of-puppets/the-thing-that-should-not-be.txt','00:06:36','1986-03-03'),(4,2,1,'Welcome Home (Sanitarium)','res/albums/lyrics/master-of-puppets/welcome-home-sanitarium.txt','00:06:27','1986-03-03'),(5,2,1,'Disposable Heroes','res/albums/lyrics/master-of-puppets/disposable-heroes.txt','00:08:16','1986-03-03'),(6,2,1,'Leper Messiah','res/albums/lyrics/master-of-puppets/leper-messiah.txt','00:05:39','1986-03-03'),(7,2,1,'Orion','res/albums/lyrics/master-of-puppets/orion.txt','00:08:27','1986-03-03'),(8,2,1,'Damage, Inc.','res/albums/lyrics/master-of-puppets/damage-inc.txt','00:05:32','1986-03-03'),(9,1,2,'20th Century Fox Fanfare','res/albums/lyrics/bohemian-rhapsody/20th-century-fox-fanfare.txt','00:00:25','2018-10-19'),(10,1,2,'Somebody To Love','res/albums/lyrics/bohemian-rhapsody/somebody-to-love.txt','00:04:56','2018-10-19'),(11,1,2,'Doing All Right... revisited','res/albums/lyrics/bohemian-rhapsody/doing-all-right-revisited.txt','00:03:16','2018-10-19'),(12,1,2,'Keep Yourself Alive (Live)','res/albums/lyrics/bohemian-rhapsody/keep-yourself-alive-live.txt','00:03:56','2018-10-19'),(13,1,2,'Killer Queen','res/albums/lyrics/bohemian-rhapsody/killer-queen.txt','00:02:59','2018-10-19'),(14,1,2,'Fat Bottomed Girls (Live)','res/albums/lyrics/bohemian-rhapsody/fat-bottomed-girls-live.txt','00:04:37','2018-10-19'),(15,1,2,'Bohemian Rhapsody','res/albums/lyrics/bohemian-rhapsody/bohemian-rhapsody.txt','00:05:55','2018-10-19'),(16,1,2,'Now I\'m Here (Live)','res/albums/lyrics/bohemian-rhapsody/now-im-here-live.txt','00:04:26','2018-10-19'),(17,1,2,'Crazy Little Thing Called Love','res/albums/lyrics/bohemian-rhapsody/crazy-little-thing-called-love.txt','00:02:43','2018-10-19'),(18,1,2,'Love of My Life (Live)','res/albums/lyrics/bohemian-rhapsody/love-of-my-life-live.txt','00:04:29','2018-10-19'),(19,1,2,'We Will Rock You (Movie Mix)','res/albums/lyrics/bohemian-rhapsody/we-will-rock-you-movie-mix.txt','00:02:09','2018-10-19'),(20,1,2,'Another One Bites The Dust	','res/albums/lyrics/bohemian-rhapsody/another-one-bites-the-dust.txt','00:03:35','2018-10-19'),(21,1,2,'I Want To Break Free','res/albums/lyrics/bohemian-rhapsody/i-want-to-break-free.txt','00:03:43','2018-10-19'),(22,1,2,'Under Pressure','res/albums/lyrics/bohemian-rhapsody/under-pressure.txt','00:03:55','2018-10-19'),(23,1,2,'Who Wants To Live Forever','res/albums/lyrics/bohemian-rhapsody/who-wants-to-live-forever.txt','00:05:15','2018-10-19'),(24,1,2,'Bohemian Rhapsody (Live Aid)','res/albums/lyrics/bohemian-rhapsody/bohemian-rhapsody-live-aid.txt','00:02:28','2018-10-19'),(25,1,2,'Radio Ga Ga (Live Aid)','res/albums/lyrics/bohemian-rhapsody/radio-ga-ga-live-aid.txt','00:04:06','2018-10-19'),(26,1,2,'Ay-Oh (Live Aid)','res/albums/lyrics/bohemian-rhapsody/ay-oh-live-aid.txt','00:00:41','2018-10-19'),(27,1,2,'Hammer To Fall (Live Aid)','res/albums/lyrics/bohemian-rhapsody/hammer-to-fall-live-aid.txt','00:04:03','2018-10-19'),(28,1,2,'We Are The Champions (Live Aid)','res/albums/lyrics/bohemian-rhapsody/we-are-the-champions-live-aid.txt','00:03:57','2018-10-19'),(29,1,2,'Don\'t Stop Me Now (Revisited)','res/albums/lyrics/bohemian-rhapsody/dont-stop-me-now-revisited.txt','00:03:38','2018-10-19'),(30,1,2,'The Show Must Go On','res/albums/lyrics/bohemian-rhapsody/the-show-must-go-on.txt','00:04:32','2018-10-19'),(31,1,3,'Smells Like Teen Spirit','res/albums/lyrics/nevermind/smells-like-teen-spirit.txt','00:05:01','1991-09-26'),(32,1,3,'In Bloom','res/albums/lyrics/nevermind/in-bloom.txt','00:04:14','1991-09-26'),(33,1,3,'Come As You Are','res/albums/lyrics/nevermind/come-as-you-are.txt','00:03:39','1991-09-26'),(34,1,3,'Breed','res/albums/lyrics/nevermind/breed.txt','00:03:39','1991-09-26'),(35,1,3,'Lithium','res/albums/lyrics/nevermind/lithium.txt','00:04:17','1991-09-26'),(36,1,3,'Polly','res/albums/lyrics/nevermind/polly.txt','00:02:57','1991-09-26'),(37,1,3,'Territorial Pissings','res/albums/lyrics/nevermind/territorial-pissings.txt','00:02:23','1991-09-26'),(38,1,3,'Drain You','res/albums/lyrics/nevermind/drain-you.txt','00:03:44','1991-09-26'),(39,1,3,'Lounge Act','res/albums/lyrics/nevermind/lounge-act.txt','00:02:36','1991-09-26'),(40,1,3,'Stay Away','res/albums/lyrics/nevermind/stay-away.txt','00:03:32','1991-09-26'),(41,1,3,'On A Plain','res/albums/lyrics/nevermind/on-a-plain.txt','00:03:16','1991-09-26'),(42,1,3,'Something In The Way','res/albums/lyrics/nevermind/something-in-the-way.txt','00:03:52','1991-09-26'),(43,1,3,'Endless, Nameless','res/albums/lyrics/nevermind/endless-nameless.txt','00:06:44','1991-09-26'),(44,1,4,'Don\'t Panic','res/albums/lyrics/parachutes/dont-panic.txt','00:02:17','2000-07-10'),(45,1,4,'Shiver','res/albums/lyrics/parachutes/shiver.txt','00:04:59','2000-07-10'),(46,1,4,'Spies','res/albums/lyrics/parachutes/spies.txt','00:05:18','2000-07-10'),(47,1,4,'Sparks','res/albums/lyrics/parachutes/sparks.txt','00:03:47','2000-07-10'),(48,1,4,'Yellow','res/albums/lyrics/parachutes/yellow.txt','00:04:29','2000-07-10'),(49,1,4,'Trouble','res/albums/lyrics/parachutes/trouble.txt','00:04:30','2000-07-10'),(50,1,4,'Parachutes','res/albums/lyrics/parachutes/parachutes.txt','00:00:46','2000-07-10'),(51,1,4,'High Speed','res/albums/lyrics/parachutes/high-speed.txt','00:04:14','2000-07-10'),(52,1,4,'We Never Change','res/albums/lyrics/parachutes/we-never-change.txt','00:04:09','2000-07-10'),(53,1,4,'Everything\'s Not Lost','Everything\'s Not Lost','00:07:17','2000-07-10'),(54,3,5,'!!!!!!!','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/!!!!!!.txt','00:00:14','2019-03-29'),(55,3,5,'Bad Guy','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/bad-guy.txt','00:03:14','2019-03-29'),(56,3,5,'Xanny','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/xanny.txt','00:04:03','2019-03-29'),(57,3,5,'You Should See Me In A Crown','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/you-should-see-me-in-a-crown.txt','00:03:00','2019-03-29'),(58,3,5,'All The Good Girls Go To Hell','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/all-the-good-girls-go-to-hell.txt','00:02:49','2019-03-29'),(59,3,5,'Wish You Were Gay','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/wish-you-were-gay.txt','00:03:42','2019-03-29'),(60,3,5,'When The Party\'s Over','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/when-the-partys-over.txt','00:03:16','2019-03-29'),(61,3,5,'8','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/8.txt','00:02:53','2019-03-29'),(62,3,5,'My Strange Addiction','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/my-strange-addiction.txt','00:03:00','2019-03-29'),(63,3,5,'Bury A Friend','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/bury-a-friend.txt','00:03:13','2019-03-29'),(64,3,5,'Ilomilo','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/ilomilo.txt','00:02:36','2019-03-29'),(65,3,5,'Listen Before I Go','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/listen-before-i-go.txt','00:04:02','2019-03-29'),(66,3,5,'I Love You','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/i-love-you.txt','00:04:52','2019-03-29'),(67,3,5,'Goodbye','res/albums/lyrics/when-we-all-fall-asleep-where-do-we-go/goodbye.txt','00:01:59','2019-03-29'),(68,10,6,'Ciega, sordomuda','res/albums/lyrics/donde-estan-los-ladrones/ciega-sordomuda.txt','00:04:27','1998-09-29'),(69,10,6,'Si te vas','res/albums/lyrics/donde-estan-los-ladrones/si-te-vas.txt','00:03:32','1998-09-29'),(70,10,6,'Moscas en la casa','res/albums/lyrics/donde-estan-los-ladrones/moscas-en-la-casa.txt','00:03:31','1998-09-29'),(71,10,6,'No creo','res/albums/lyrics/donde-estan-los-ladrones/no-creo.txt','00:03:51','1998-09-29'),(72,10,6,'Inevitable','res/albums/lyrics/donde-estan-los-ladrones/inevitable.txt','00:03:14','1998-09-29'),(73,10,6,'Octavo día','res/albums/lyrics/donde-estan-los-ladrones/octavo-dia.txt','00:04:10','1998-09-29'),(74,10,6,'Que vuelvas','res/albums/lyrics/donde-estan-los-ladrones/que-vuelvas.txt','00:04:04','1998-09-29'),(75,10,6,'Tú','res/albums/lyrics/donde-estan-los-ladrones/tu.txt','00:04:39','1998-09-29'),(76,10,6,'Dónde están los ladrones','res/albums/lyrics/donde-estan-los-ladrones/donde-estan-los-ladrones.txt','00:03:13','1998-09-29'),(77,10,6,'Sombra de ti','res/albums/lyrics/donde-estan-los-ladrones/sombra-de-ti.txt','00:03:37','1998-09-29'),(78,10,6,'Ojos así','res/albums/lyrics/donde-estan-los-ladrones/ojos-asi.txt','00:04:22','1998-09-29'),(79,4,7,'Talento de barrio','res/albums/lyrics/talento-de-barrio/talento-de-barrio.txt','00:03:34','2008-07-28'),(80,4,7,'Pose','res/albums/lyrics/talento-de-barrio/pose.txt','00:03:32','2008-07-28'),(81,4,7,'Somos de calle','res/albums/lyrics/talento-de-barrio/somos-de-calle.txt','00:04:09','2008-07-28'),(82,4,7,'Llamado de emergencia','res/albums/lyrics/talento-de-barrio/llamado-de-emergencia.txt','00:03:49','2008-07-28'),(83,4,7,'Coraza divina','res/albums/lyrics/talento-de-barrio/coraza-divina.txt','00:04:07','2008-07-28'),(84,4,7,'Salgo pa\' la calle','res/albums/lyrics/talento-de-barrio/salgo-pa-la-calle.txt','00:03:42','2008-07-28'),(85,4,7,'Me quedo contigo','res/albums/lyrics/talento-de-barrio/me-quedo-contigo.txt','00:03:36','2008-07-28'),(86,4,7,'Pa\' kum pa\'','res/albums/lyrics/talento-de-barrio/pa-kum-pa.txt','00:03:13','2008-07-28'),(87,4,7,'Que tengo que hacer','res/albums/lyrics/talento-de-barrio/que-tengo-que-hacer.txt','00:03:43','2008-07-28'),(88,4,7,'Sabor a melao','res/albums/lyrics/talento-de-barrio/sabor-a-melao.txt','00:03:49','2008-07-28'),(89,4,7,'¿Qué vas a hacer?','res/albums/lyrics/talento-de-barrio/que-vas-a-hacer.txt','00:03:39','2008-07-28'),(90,4,7,'Suelta','res/albums/lyrics/talento-de-barrio/suelta.txt','00:03:23','2008-07-28'),(91,4,7,'Como yvone','res/albums/lyrics/talento-de-barrio/como-yvone.txt','00:03:38','2008-07-28'),(92,4,7,'Jefe','res/albums/lyrics/talento-de-barrio/jefe.txt','00:03:32','2008-07-28'),(93,4,7,'Solita y sola','res/albums/lyrics/talento-de-barrio/solita-y-sola.txt','00:03:53','2008-07-28'),(94,4,7,'Señor oficial','res/albums/lyrics/talento-de-barrio/senor-oficial.txt','00:03:48','2008-07-28'),(95,7,8,'Keep the Family Close','res/albums/lyrics/views/keep-the-family-close.txt','00:05:28','2016-05-06'),(96,7,8,'9','res/albums/lyrics/views/9.txt','00:04:15','2016-05-06'),(97,7,8,'U With Me?','res/albums/lyrics/views/u-with-me.txt','00:04:57','2016-05-06'),(98,7,8,'Feel No Ways','res/albums/lyrics/views/feel-no-ways.txt','00:04:00','2016-05-06'),(99,7,8,'Hype','res/albums/lyrics/views/hype.txt','00:03:29','2016-05-06'),(100,7,8,'Weston Road Flows','res/albums/lyrics/views/weston-road-flows.txt','00:04:13','2016-05-06'),(101,7,8,'Redemption','res/albums/lyrics/views/redemption.txt','00:05:34','2016-05-06'),(102,7,8,'With You (feat. PARTYNEXTDOOR)','res/albums/lyrics/views/with-you.txt','00:03:15','2016-05-06'),(103,7,8,'Faithful (feat. Pimp C & dvsn)','res/albums/lyrics/views/faithful.txt','00:04:50','2016-05-06'),(104,7,8,'Still Here','res/albums/lyrics/views/still-here.txt','00:03:09','2016-05-06'),(105,7,8,'Controlla','res/albums/lyrics/views/controlla.txt','00:04:05','2016-05-06'),(106,7,8,'One Dance (feat. Wizkid & Kyla)','res/albums/lyrics/views/one-dance.txt','00:02:54','2016-05-06'),(107,7,8,'Grammys (feat. Future)','res/albums/lyrics/views/grammys.txt','00:03:39','2016-05-06'),(108,7,8,'Childs Play','res/albums/lyrics/views/childs-play.txt','00:04:01','2016-05-06'),(109,7,8,'Pop Style','res/albums/lyrics/views/pop-style.txt','00:03:32','2016-05-06'),(110,7,8,'Too Good (feat. Rihanna)','res/albums/lyrics/views/too-good.txt','00:04:23','2016-05-06'),(111,7,8,'Summers Over Interlude','res/albums/lyrics/views/summers-over-interlude.txt','00:01:51','2016-05-06'),(112,7,8,'Fire & Desire','res/albums/lyrics/views/fire-and-desire.txt','00:03:58','2016-05-06'),(113,7,8,'Views','res/albums/lyrics/views/views.txt','00:05:01','2016-05-06'),(114,7,8,'Hotline Bling (bonus track)','res/albums/lyrics/views/hotline-bling.txt','00:04:27','2016-05-06'),(115,9,9,'Rolling in the Deep','res/albums/lyrics/21/rolling-in-the-deep.txt','00:03:48','2011-01-24'),(116,9,9,'Rumour Has It','res/albums/lyrics/21/rumour-has-it.txt','00:03:43','2011-01-24'),(117,9,9,'Turning Tables','res/albums/lyrics/21/turning-tables.txt','00:04:10','2011-01-24'),(118,9,9,'Don\'t You Remember','res/albums/lyrics/21/dont-you-remember.txt','00:04:03','2011-01-24'),(119,9,9,'Set Fire to the Rain','res/albums/lyrics/21/set-fire-to-the-rain.txt','00:04:02','2011-01-24'),(120,9,9,'He Won\'t Go','res/albums/lyrics/21/he-wont-go.txt','00:04:38','2011-01-24'),(121,9,9,'Take It All','res/albums/lyrics/21/take-it-all.txt','00:03:48','2011-01-24'),(122,9,9,'I\'ll Be Waiting','res/albums/lyrics/21/ill-be-waiting.txt','00:04:01','2011-01-24'),(123,9,9,'One and Only','res/albums/lyrics/21/one-and-only.txt','00:05:48','2011-01-24'),(124,9,9,'Lovesong','res/albums/lyrics/21/lovesong.txt','00:05:16','2011-01-24'),(125,9,9,'Someone Like You','res/albums/lyrics/21/someone-like-you.txt','00:04:45','2011-01-24'),(126,8,10,'Do I Wanna Know?','res/albums/lyrics/am/do-i-wanna-know.txt','00:04:32','2013-09-09'),(127,8,10,'R U Mine?','res/albums/lyrics/am/r-u-mine.txt','00:03:21','2013-09-09'),(128,8,10,'One for the Road','res/albums/lyrics/am/one-for-the-road.txt','00:03:26','2013-09-09'),(129,8,10,'Arabella','res/albums/lyrics/am/arabella.txt','00:03:27','2013-09-09'),(130,8,10,'I Want It All','res/albums/lyrics/am/i-want-it-all.txt','00:03:05','2013-09-09'),(131,8,10,'No. 1 Party Anthem','res/albums/lyrics/am/no-1-party-anthem.txt','00:04:03','2013-09-09'),(132,8,10,'Mad Sounds','res/albums/lyrics/am/mad-sounds.txt','00:03:35','2013-09-09'),(133,8,10,'Fireside','res/albums/lyrics/am/fireside.txt','00:03:01','2013-09-09'),(134,8,10,'Why\'d You Only Call Me When You\'re High?','res/albums/lyrics/am/whyd-you-only-call-me-when-youre-high.txt','00:02:41','2013-09-09'),(135,8,10,'Snap Out of It','res/albums/lyrics/am/snap-out-of-it.txt','00:03:13','2013-09-09'),(136,8,10,'Knee Socks','res/albums/lyrics/am/knee-socks.txt','00:04:17','2013-09-09'),(137,8,10,'I Wanna Be Yours','res/albums/lyrics/am/i-wanna-be-yours.txt','00:03:04','2013-09-09');



CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `genre` VALUES (1,'Rock'),(2,'Metal'),(3,'Pop'),(4,'Reguetón'),(5,'Alternativo'),(6,'Electrónica'),(7,'R&B'),(8,'Indie Rock'),(9,'Pop Soul'),(10,'Latin Pop'),(11,'Regional mexicano');

CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discography_id` int(11) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `release_date` date NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
--   KEY `discography_id` (`discography_id`),
--   CONSTRAINT `album_ibfk_1` FOREIGN KEY (`discography_id`) REFERENCES `discography` (`discography_id`) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO `album` VALUES (1,1,'Master Of Puppets','1986-03-03','res/albums/img/master-of-puppets.jpg'),(2,2,'Bohemian Rhapsody: The Original Soundtrack','2018-10-19','res/albums/img/bohemian-rhapsody.jpg'),(3,3,'Nevermind','1991-09-26','res/albums/img/nevermind.jpg'),(4,4,'Parachutes','2000-07-10','res/albums/img/parachutes.jpg'),(5,5,'When We All Fall Asleep, Where Do We Go?','2019-03-29','res/albums/img/when-we-all-fall-Asleep-where-do-we-go.jpg'),(6,6,'¿Dónde están los ladrones?','1998-09-29','res/albums/img/donde-estan-los-ladrones.jpg'),(7,7,'Talento de barrio','2008-07-28','res/albums/img/talento-de-barrio.jpg'),(8,8,'Views','2016-05-06','res/albums/img/views.jpg'),(9,9,'21','2011-01-24','res/albums/img/21-adele.jpg'),(10,10,'AM','2013-09-09','res/albums/img/am.jpg');

CREATE TABLE artist_album (
    artist_id INT,
    album_id INT,
    PRIMARY KEY (artist_id, album_id),
    FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE,
    FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE
);

INSERT INTO `artist_album` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10);

CREATE TABLE `discography` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `logo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `discography` VALUES (1,'Elektra Record','res/discografia/img/elektra-record.png'),(2,'Virgin EMI','res/discografia/img/virgin-emi.png'),(3,'DGC Records','res/discografia/img/dgc-records.png'),(4,'Parlophone Records Ltd','res/discografia/img/parlophone-records-ltd.png'),(5,'Interscope Records','res/discografia/img/interscope-records.png'),(6,'Sony Music Latin','res/discografia/img/sony-music-latin.png'),(7,'El Cartel Records','res/discografia/img/el-cartel-records.png'),(8,'Cash Money Records','res/discografia/img/cash-money-records.png'),(9,'XL Recordings Ltd','res/discografia/img/xl-recordings-ltd.png'),(10,'Domino Records','res/discografia/img/domino-records.png');

