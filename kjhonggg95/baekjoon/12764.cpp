#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, p, q, ans;
int freq[100001];     // 자리별 앉은 횟수
bool seat[100001];    // 착석 가능 여부
map<pii, int> seatNo; // 시작/종료 시간 별 몇번자리에 앉아있는지

struct comp
{
    bool operator()(pii p1, pii p2)
    {
        if (p1.Y == p2.Y)
            return p1.X > p2.X;
        return p1.Y > p2.Y;
    }
};

// 시작 시각이나 종료 시각이 다른 사람과 겹치는 경우는 없다
// --> 시작/종료 시간 별 몇 번 자리에 앉았는지 독립적으로 저장 가능

int main()
{
    fastio;
    cin >> n;
    priority_queue<pii, vector<pii>, greater<pii>> in;
    priority_queue<pii, vector<pii>, comp> out;
    priority_queue<int, vector<int>, greater<int>> mnseat;
    for (int i = 1; i <= n; i++)
        mnseat.push(i);

    for (int i = 0; i < n; i++)
    {
        cin >> p >> q;
        in.push({p, q});
    }

    // 입장시간 순
    // 10 100
    // 20 50
    // 30 120
    // 60 110
    // 80 90

    // 퇴장시간 순
    // 20 50
    // 80 90
    // 10 100
    // 60 110
    // 30 120

    for (int t = 0; t <= 1000000; t++)
    {
        while (!in.empty() && t == in.top().X) // 입장 시간
        {
            auto cur = in.top();
            out.push(cur);
            in.pop();

            // 착석
            // int k = findSeat(); // 앉을 수 있는 자리 중 번호가 가장 작은 자리
            int k = mnseat.top();
            mnseat.pop();
            seat[k] = true;
            freq[k]++;
            seatNo[cur] = k;
        }

        while (!out.empty() && t == out.top().Y) // 퇴장 시간
        {
            auto cur = out.top();
            out.pop();

            // 종료
            int k = seatNo[cur];
            seat[k] = false;
            mnseat.push(k);
        }

        ans = max(ans, (int)out.size());
    }

    cout << ans << '\n';

    for (int i = 1; i <= ans; i++)
        cout << freq[i] << ' ';
    cout << '\n';
}
