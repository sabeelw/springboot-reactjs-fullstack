import { useParams } from "react-router-dom";
import { CardUser as UserCard } from "../../components/card-component/card-component";
import { useEffect, useState } from "react"
import { SampleContainer } from "../../components/container-component/container-component";
export const UserView = () => {
    let [user, setUser] = useState([]);
    const { id } = useParams()
    useEffect(() => {
        fetch("/user/" + id).then(r => r.json()).then(
            r => {
                setUser(r)
            }
        )
    }, []
    )
    return (
        <SampleContainer>
            <UserCard name={user.name} email={user.email}></UserCard>
        </SampleContainer>
    )
}