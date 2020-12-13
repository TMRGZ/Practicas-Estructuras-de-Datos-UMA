import Test.QuickCheck

replicate' :: Int -> a -> [a]
replicate' n x = [x | y<-[1..n]]

p_replicate' :: Eq a => Int -> a -> Property
p_replicate' n x = n>=0 && n<=1000 ==> 
                            length (filter(==x) xs) == n
                            && length (filter(/=x) xs) ==0
                                where xs = replicate' n x 