import { CardUser } from "../../components/card-component/card-component"
import { useEffect, useState } from "react"
import { SampleContainer } from "../../components/container-component/container-component";
import { Form } from "../../components/form-component/form-component";


export const HomeView = () => {
    let [users, setUsers] = useState([]);
    let [formData, setFormData] = useState({})
    const onChangeHandler = ({ target }) => {
        setFormData({ ...formData, [target.name.toLowerCase()]: target.value })
    }
    const fetchData = async () => {
        await fetch("/users").then(r => r.json()).then(
            r =>
                setUsers(r.reverse())
        )
    }
    const registerHandler = async (e) => {
        e.preventDefault();
        try {
            const a = await fetch("/createUser", {
                method: "POST",
                body: JSON.stringify(formData),
                headers: {
                    "Content-Type": "application/json",
                },
            })
            if (a.ok) {
                fetchData()
            } else {
                throw Error("Some error");
            }
        } catch (e) {
            console.log("some problem")
        }
    }

    useEffect(() => {
        fetchData()
    }, []
    )
    return (
        <SampleContainer>
            <Form onChangeHandler={onChangeHandler} onClick={registerHandler}></Form>
            <h1>Users: </h1>
            {users.map((user, id) =>
                <div style={{ padding: "5px" }} key={id}>
                    <CardUser user={user} />
                </div>
            )}

        </SampleContainer>
    )
}

