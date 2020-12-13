takeprueba :: Int -> [a] -> [a]
takeprueba 0 _ = []
takeprueba n [] = []
takeprueba n (x:xs) = x : takeprueba (n-1) xs

dropprueba :: Int -> [a] -> [a]
dropprueba 0 xs = xs
dropprueba n [] = []
dropprueba n (x:xs) = dropprueba (n-1) xs

takeWhilePrueba :: (a->Bool) -> [a] -> [a]
takeWhilePrueba  p [] = []
takeWhilePrueba p (x:xs)
    |p x = x:takeWhilePrueba p xs
    |otherwise = []


dropWhilePrueba :: (a->Bool) -> [a] -> [a]



zipWhilePrueba :: (a->b->c) -> [a]->[b]->[c]