import Test.QuickCheck

max2 :: Ord a => a -> a -> a
max2 x y
    |x>y = x
    |otherwise = y

p1_max2 x y = True ==> max2 x y == x || max2 x y == y

p2_max2 x y = True ==> max2 x y >= x || max2 x y >= y

p3_max2 x y = x>=y ==> max2 x y == x

p4_max2 x y = y>=x ==> max2 x y == y