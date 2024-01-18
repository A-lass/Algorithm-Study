#include <iostream>
#include <algorithm>
#include <cstring>
#include <deque>

using namespace std;
using ll = long long;
using pii = pair<int, int>;

int cnt_score[101];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    string s;
    cin >> s;
    deque<char> dq;
    dq.push_front(s[0]);
    for (int i = 1; i < s.length(); ++i) {
         if (s[i] <= dq[0]) {
             dq.push_front(s[i]);
         } else {
             dq.push_back(s[i]);
         }
    }
    for (auto c : dq) {
        cout << c;
    }

    return 0;
}
