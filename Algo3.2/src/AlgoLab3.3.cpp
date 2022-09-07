#include <iostream>
#include <string>
int main()
{
    int a, b, c, d;
    std::string s;
    int m;
    std::cin >> s;
    std::cin >> m;
    for (int i = 0; i < m; i++)
    {
        std::cin >> a;
        std::cin >> b;
        std::cin >> c;
        std::cin >> d;
        a--;
        c--;
        int size1 = b - a;
        int size2 = d - c;
        if ((size1 == size2) && memcmp(&s[a], &s[c], size1) == 0) {
            std::cout << "Yes" << std::endl;
        }
        else {
            std::cout << "No" << std::endl;
        }
    }
}

