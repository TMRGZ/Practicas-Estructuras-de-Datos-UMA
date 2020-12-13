import Test.QuickCheck

square x = x*x

p1 x y = True ==> square(x+y) == square x + square y + 2*x*y

p2 x y = True ==> abs (x+y) == abs x + abs y

p3 x y = x>0 && y>0 ==> abs (x+y) == abs x + abs y

fib :: Integer -> Integer
fib n
    |n==0 = 0
    |n==1 = 1
    |otherwise = fib (n-1) + fib(n-2)

resto :: Integer -> Integer -> Integer
resto x y
    |x<y = x
    |otherwise = resto (x-y) y

cociente :: Integer -> Integer -> Integer
cociente x y
    |x<y =0
    |otherwise = 1+ cociente (x-y) y

p4 x y = x>0 && y>0 ==> cociente x y == div x y

cocienteyresto :: Integer -> Integer -> (Integer, Integer)  --(cociente, resto)
cocienteyresto x y
    |x<y = (0,x)
    |otherwise = (u+1, v)
        where (u,v) = cocienteyresto (x-y) y